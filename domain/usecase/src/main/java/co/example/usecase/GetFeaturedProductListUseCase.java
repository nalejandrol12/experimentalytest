package co.example.usecase;

import co.example.entities.Product;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
public class GetFeaturedProductListUseCase {

    private final ProductGateway productGateway;
    private final Logger logger = Loggers.getLogger(GetFeaturedProductListUseCase.class);

    public Flux<Product> execute() {
        return productGateway.findByFeaturedProduct().onErrorResume(error -> {
            logger.error(error.getMessage());
            return Mono.error(new Throwable("Error executing the query createProduct"));
        });
    }
}
