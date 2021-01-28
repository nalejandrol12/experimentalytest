package co.example.usecase;

import co.example.entities.CompleteProduct;
import co.example.enums.StatusCodeEnum;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductGateway productGateway;
    private static final String ERROR_IN_PRICE_DISCOUNT = "The discount price is wrong";
    private final Logger logger = Loggers.getLogger(CreateProductUseCase.class);

    public Mono<Tuple3<CompleteProduct, Integer, Object>> execute(CompleteProduct completeProduct) {
        return Mono.just(validationParams(completeProduct))
                .flatMap(validation -> validation
                        ? productGateway.createProduct(completeProduct)
                        .onErrorResume(error -> {
                            logger.error(error.getMessage());
                            return Mono.error(new Throwable("Error executing the query createProduct"));
                        })
                        .map(data -> Tuples.of(data, StatusCodeEnum.CREATE.getCode(), ""))
                        : Mono.just(Tuples.of(CompleteProduct.builder().build(), StatusCodeEnum.BAT_REQUEST.getCode(),
                        ERROR_IN_PRICE_DISCOUNT)));
    }

    private boolean validationParams(CompleteProduct completeProduct) {

        Double priceDiscount = completeProduct.getPrice() - (completeProduct.getPrice()
                * completeProduct.getDiscountRate());

        return completeProduct.getPriceDiscount().equals(priceDiscount);
    }
}
