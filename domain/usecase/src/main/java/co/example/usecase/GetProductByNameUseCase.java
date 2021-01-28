package co.example.usecase;

import co.example.enums.NameParamsFindByNameEnum;
import co.example.entities.Pagination;
import co.example.entities.PaginationProduct;
import co.example.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.Map;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class GetProductByNameUseCase {
    private final ProductGateway productGateway;
    private final Logger logger = Loggers.getLogger(GetProductByNameUseCase.class);

    public Mono<PaginationProduct> execute(Map<String, Object> params) {
        return productGateway.findByName(params)
                .flatMap(products -> productGateway.countByProductName(params
                        .get(NameParamsFindByNameEnum.NAME.getParam()).toString())
                        .onErrorResume(error -> {
                            logger.error(error.getMessage());
                            return Mono.error(new Throwable("Error executing the query countByProduct"));
                        })
                        .map(totalProducts -> PaginationProduct.builder()
                                .products(products)
                                .pagination(buildPagination.apply(totalProducts, params))
                                .build()))
                .onErrorResume(error -> {
                    logger.error(error.getMessage());
                    return Mono.error(new Throwable("Error executing the query findByName"));
                });
    }


    private final BiFunction<Integer, Map<String, Object>, Pagination> buildPagination = (totalProduct, params) ->
            Pagination.builder()
                    .currentPage((int) params.get(NameParamsFindByNameEnum.PAGE.getParam()))
                    .totalProducts(totalProduct)
                    .totalPages(calculateTotalPages(totalProduct,
                            (int) params.get(NameParamsFindByNameEnum.SIZE.getParam())))
                    .build();


    private int calculateTotalPages(int totalProduct, int size) {
        return size > 0 ? (totalProduct - 1) / size + 1 : 0;
    }
}
