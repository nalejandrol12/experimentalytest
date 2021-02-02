package co.example.usecase;

import co.example.entities.CompleteProduct;
import co.example.exception.ProductException;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductGateway productGateway;
    private static final String ERROR_IN_PRICE_DISCOUNT = "The discount price is wrong";
    private static final String ERROR_EXECUTING_QUERY = "Error executing the query createProduct";
    private final Logger logger = Loggers.getLogger(CreateProductUseCase.class);

    public Mono<CompleteProduct> execute(CompleteProduct completeProduct) {
        return Mono.just(validationParams(completeProduct))
                .flatMap(validation -> validation
                        ? productGateway.createProduct(completeProduct)
                        .onErrorResume(error -> {
                            logger.error(error.getMessage());
                            return Mono.error(new Throwable(ERROR_EXECUTING_QUERY));
                        })
                        : Mono.error(new ProductException(ERROR_IN_PRICE_DISCOUNT)));
    }

    private boolean validationParams(CompleteProduct completeProduct) {

        Double priceDiscount = completeProduct.getPrice() - (completeProduct.getPrice()
                * completeProduct.getDiscountRate());

        return completeProduct.getPriceDiscount().equals(priceDiscount);
    }
}
