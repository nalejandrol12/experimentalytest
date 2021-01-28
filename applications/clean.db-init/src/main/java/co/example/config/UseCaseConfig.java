package co.example.config;

import co.example.gateway.ProductGateway;
import co.example.usecase.FindProductByNameUseCase;
import co.example.usecase.FindProductDetailByIdUseCase;
import co.example.usecase.GetFeaturedProductListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public GetFeaturedProductListUseCase getFeaturedProductListUseCase(ProductGateway productGateway) {
        return new GetFeaturedProductListUseCase(productGateway);
    }

    @Bean
    public FindProductByNameUseCase findProductByNameUseCase(ProductGateway productGateway) {
        return new FindProductByNameUseCase(productGateway);
    }

    @Bean
    public FindProductDetailByIdUseCase findProductDetailByIdUseCase(ProductGateway productGateway) {
        return new FindProductDetailByIdUseCase(productGateway);
    }
}