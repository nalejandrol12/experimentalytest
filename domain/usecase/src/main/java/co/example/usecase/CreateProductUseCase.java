package co.example.usecase;

import co.example.entities.CompleteProduct;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductGateway productGateway;

    public Mono<CompleteProduct> execute(CompleteProduct completeProduct) {
        return productGateway.createProduct(completeProduct);
    }
}
