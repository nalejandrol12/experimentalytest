package co.example.usecase;

import co.example.entities.PaginationProduct;
import co.example.entities.Product;
import co.example.enums.NameParamsFindByNameEnum;
import co.example.gateway.ProductGateway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GetProductByNameUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private GetProductByNameUseCase useCase;

    private static final String TEST_STRING = "TEST";

    private Map<String, Object> params = new HashMap<>();

    private Product product = Product.builder().build();

    @Before
    public void init() {

        params.put(NameParamsFindByNameEnum.NAME.getParam(), "TEST");
        params.put(NameParamsFindByNameEnum.PAGE.getParam(), 1);
        params.put(NameParamsFindByNameEnum.SIZE.getParam(), 1);

        product.toBuilder()
                .id(TEST_STRING)
                .backImage(TEST_STRING)
                .discountRate(0.5)
                .frontImage(TEST_STRING)
                .name(TEST_STRING)
                .price(1000.0)
                .priceDiscount(500.0)
                .build();
    }


    @Test
    public void executeTest() {

        when(productGateway.findByName(any()))
                .thenReturn(Mono.just(Collections.singletonList(product)));

        when(productGateway.countByProductName(TEST_STRING))
                .thenReturn(Mono.just(1));

        Mono<PaginationProduct> responseUseCase = useCase.execute(params);

        StepVerifier.create(responseUseCase)
                .assertNext(data -> Assert.assertNotNull(data.getPagination()))
                .verifyComplete();
    }

    @Test
    public void executeWhenFindByNameReturnErrorTest() {

        when(productGateway.findByName(any()))
                .thenReturn(Mono.error(new Throwable("Error")));

        when(productGateway.countByProductName(TEST_STRING))
                .thenReturn(Mono.just(1));

        Mono<PaginationProduct> responseUseCase = useCase.execute(params);

        StepVerifier.create(responseUseCase)
                .verifyError();
    }

    @Test
    public void executeWhenCountByProductNameReturnErrorTest() {

        when(productGateway.findByName(any()))
                .thenReturn(Mono.just(Collections.singletonList(product)));

        when(productGateway.countByProductName(TEST_STRING))
                .thenReturn(Mono.error(new Throwable("Error")));

        Mono<PaginationProduct> responseUseCase = useCase.execute(params);

        StepVerifier.create(responseUseCase)
                .verifyError();
    }

}
