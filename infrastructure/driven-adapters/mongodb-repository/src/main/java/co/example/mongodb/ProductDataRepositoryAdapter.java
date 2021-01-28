package co.example.mongodb;

import co.example.entities.CompleteProduct;
import co.example.entities.ProductDetail;
import co.example.enums.NameParamsFindByNameEnum;
import co.example.entities.Product;
import co.example.mongodb.builder.ProductBuilder;
import co.example.mongodb.interfaces.ProductDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductDataRepositoryAdapter {

    private final ProductDataRepository productDataRepository;

    public Flux<Product> findByFeaturedProduct() {
        return productDataRepository.findByFeaturedProductIsTrue()
                .map(ProductBuilder::convertToProduct);
    }

    public Mono<List<Product>> findByName(Map<String, Object> params) {
        Pageable page = PageRequest.of((int) params.get(NameParamsFindByNameEnum.PAGE.getParam()),
                (int) params.get(NameParamsFindByNameEnum.SIZE.getParam()));

        return productDataRepository.findByName(params
                .get(NameParamsFindByNameEnum.NAME.getParam()).toString(), page)
                .collectList()
                .map(ProductBuilder::convertToProductList);
    }

    public Mono<Integer> countByName(String name) {
        return productDataRepository.countByName(name).map(totalProducts
                -> totalProducts.intValue());
    }

    public Mono<ProductDetail> findProductDetailById(String id) {
        return productDataRepository.findById(id)
                .map(ProductBuilder::convertToProductDetail);
    }

    public Mono<CompleteProduct> createProduct(CompleteProduct completeProduct) {
        return productDataRepository.save(ProductBuilder.convertToCompleteProductData(completeProduct))
                .map(ProductBuilder::convertToCompleteProduct);
    }

}
