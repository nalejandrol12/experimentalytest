package co.example.usecase;

import co.example.entities.Product;
import co.example.gateway.ProductGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GetFeaturedProductListUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private GetFeaturedProductListUseCase getFeaturedProductListUseCase;


    @Test
    public void executeTest() {

        Product product = Product.builder().build();

        when(productGateway.findByFeaturedProduct())
                .thenReturn(Flux.just(product));

        Flux<Product> productFlux = getFeaturedProductListUseCase.execute();

        StepVerifier.create(productFlux)
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    public void executeWhenReturnErrorTest() {

        when(productGateway.findByFeaturedProduct())
                .thenReturn(Flux.error(new Throwable("Error")));

        Flux<Product> productFlux = getFeaturedProductListUseCase.execute();

        StepVerifier.create(productFlux)
                .verifyError();
    }
}
