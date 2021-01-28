package co.example.usecase;

import co.example.entities.ProductDetail;
import co.example.gateway.ProductGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GetProductDetailByIdUseCaseTest {
    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private GetProductDetailByIdUseCase GetProductDetailByIdUseCase;

    private static final String TEST_STRING = "TEST";

    @Test
    public void executeTest() {

        ProductDetail productDetail = ProductDetail.builder().build();

        when(productGateway.findProductDetailById(anyString()))
                .thenReturn(Mono.just(productDetail));

        Mono<ProductDetail> productFlux = GetProductDetailByIdUseCase.execute(TEST_STRING);

        StepVerifier.create(productFlux)
                .expectNext(productDetail)
                .verifyComplete();
    }

    @Test
    public void executeWhenReturnErrorTest() {

        when(productGateway.findProductDetailById(anyString()))
                .thenReturn(Mono.error(new Throwable("Error")));

        Mono<ProductDetail> productFlux = GetProductDetailByIdUseCase.execute(TEST_STRING);

        StepVerifier.create(productFlux)
                .verifyError();
    }
}
