package co.example.usecase;

import co.example.entities.ProductDetail;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindProductDetailByIdUseCase {
    private final ProductGateway productGateway;

    public Mono<ProductDetail> execute(String id) {
        return productGateway.findProductDetailById(id);
    }
}
