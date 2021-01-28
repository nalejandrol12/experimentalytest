package co.example.usecase;

import co.example.entities.ProductDetail;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
public class GetProductDetailByIdUseCase {
    private final ProductGateway productGateway;
    private final Logger logger = Loggers.getLogger(GetProductDetailByIdUseCase.class);


    public Mono<ProductDetail> execute(String id) {
        return productGateway.findProductDetailById(id).onErrorResume(error -> {
            logger.error(error.getMessage());
            return Mono.error(new Throwable("Error executing the query findProductDetailById"));
        });
    }
}
