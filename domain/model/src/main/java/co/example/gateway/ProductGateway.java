package co.example.gateway;

import co.example.entities.CompleteProduct;
import co.example.entities.Product;
import co.example.entities.ProductDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ProductGateway {
    Flux<Product> findByFeaturedProduct();

    Mono<List<Product>> findByName(Map<String, Object> params);

    Mono<Integer> countByProductName(String productName);

    Mono<ProductDetail> findProductDetailById(String id);

    Mono<CompleteProduct> createProduct(CompleteProduct completeProduct);
}
