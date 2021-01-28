package co.example.usecase;

import co.example.entities.Product;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetFeaturedProductListUseCase {

    private final ProductGateway productGateway;

    public Flux<Product> execute(){
        return productGateway.findByFeaturedProduct();
    }
}
