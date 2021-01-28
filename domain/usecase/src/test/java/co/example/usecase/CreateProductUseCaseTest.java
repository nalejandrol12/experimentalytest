package co.example.usecase;

import co.example.entities.CompleteProduct;
import co.example.gateway.ProductGateway;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple3;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CreateProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private CreateProductUseCase useCase;

    private static final String TEST_STRING = "TEST";


    @Test
    public void executeTest() {

        CompleteProduct completeProduct = CompleteProduct.builder()
                .id(TEST_STRING)
                .backImage(TEST_STRING)
                .discountRate(0.5)
                .frontImage(TEST_STRING)
                .name(TEST_STRING)
                .price(1000.0)
                .priceDiscount(500.0)
                .description(TEST_STRING)
                .otherImages(Collections.singletonList(TEST_STRING))
                .featuredProduct(true)
                .build();

        when(productGateway.createProduct(any(CompleteProduct.class)))
                .thenReturn(Mono.just(completeProduct));

        Mono<Tuple3<CompleteProduct, Integer, Object>> responseUseCase = useCase.execute(completeProduct);

        StepVerifier.create(responseUseCase)
                .assertNext(data -> Assert.assertNotNull(data.getT1()))
                .verifyComplete();
    }

    @Test
    public void executeWhenValidationIsFalseTest() {

        CompleteProduct completeProduct = CompleteProduct.builder()
                .id(TEST_STRING)
                .backImage(TEST_STRING)
                .discountRate(0.5)
                .frontImage(TEST_STRING)
                .name(TEST_STRING)
                .price(1000.0)
                .priceDiscount(600.0)
                .description(TEST_STRING)
                .otherImages(Collections.singletonList(TEST_STRING))
                .featuredProduct(true)
                .build();

        when(productGateway.createProduct(any(CompleteProduct.class)))
                .thenReturn(Mono.just(completeProduct));

        Mono<Tuple3<CompleteProduct, Integer, Object>> responseUseCase = useCase.execute(completeProduct);

        StepVerifier.create(responseUseCase)
                .assertNext(data -> Assert.assertNotNull(data.getT1()))
                .verifyComplete();
    }

    @Test
    public void executeWhenReturnErrorTest() {

        CompleteProduct completeProduct = CompleteProduct.builder()
                .id(TEST_STRING)
                .backImage(TEST_STRING)
                .discountRate(0.5)
                .frontImage(TEST_STRING)
                .name(TEST_STRING)
                .price(1000.0)
                .priceDiscount(500.0)
                .description(TEST_STRING)
                .otherImages(Collections.singletonList(TEST_STRING))
                .featuredProduct(true)
                .build();

        when(productGateway.createProduct(any(CompleteProduct.class)))
                .thenReturn(Mono.error(new Throwable("Error")));

        Mono<Tuple3<CompleteProduct, Integer, Object>> responseUseCase = useCase.execute(completeProduct);

        StepVerifier.create(responseUseCase)
                .verifyError();
    }
}
