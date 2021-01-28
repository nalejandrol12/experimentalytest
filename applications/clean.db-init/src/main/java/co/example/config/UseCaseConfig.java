package co.example.config;

import co.example.gateway.ProductGateway;
import co.example.usecase.CreateProductUseCase;
import co.example.usecase.GetProductByNameUseCase;
import co.example.usecase.GetProductDetailByIdUseCase;
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
    public GetProductByNameUseCase getProductByNameUseCase(ProductGateway productGateway) {
        return new GetProductByNameUseCase(productGateway);
    }

    @Bean
    public GetProductDetailByIdUseCase getProductDetailByIdUseCase(ProductGateway productGateway) {
        return new GetProductDetailByIdUseCase(productGateway);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(ProductGateway productGateway) {
        return new CreateProductUseCase(productGateway);
    }
}