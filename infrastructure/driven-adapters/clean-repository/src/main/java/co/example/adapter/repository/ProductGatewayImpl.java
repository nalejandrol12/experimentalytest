package co.example.adapter.repository;

import co.example.entities.CompleteProduct;
import co.example.entities.Product;
import co.example.entities.ProductDetail;
import co.example.gateway.ProductGateway;
import co.example.mongodb.ProductDataRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductDataRepositoryAdapter productDataRepositoryAdapter;

    @Override
    public Flux<Product> findByFeaturedProduct() {
        return productDataRepositoryAdapter.findByFeaturedProduct();
    }

    @Override
    public Mono<List<Product>> findByName(Map<String, Object> params) {
        return productDataRepositoryAdapter.findByName(params);
    }

    @Override
    public Mono<Integer> countByProductName(String name) {
        return productDataRepositoryAdapter.countByName(name);
    }

    @Override
    public Mono<ProductDetail> findProductDetailById(String id) {
        return productDataRepositoryAdapter.findProductDetailById(id);
    }

    @Override
    public Mono<CompleteProduct> createProduct(CompleteProduct completeProduct) {
        return productDataRepositoryAdapter.createProduct(completeProduct);
    }
}
