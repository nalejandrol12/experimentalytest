package co.example.controllers;

import co.example.controllers.dto.CompleteProductDto;
import co.example.controllers.factory.ProductFactory;
import co.example.entities.PaginationProduct;
import co.example.entities.Product;
import co.example.entities.ProductDetail;
import co.example.enums.NameParamsFindByNameEnum;
import co.example.exception.ProductException;
import co.example.usecase.CreateProductUseCase;
import co.example.usecase.GetFeaturedProductListUseCase;
import co.example.usecase.GetProductByNameUseCase;
import co.example.usecase.GetProductDetailByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductController<T> {

    private final GetFeaturedProductListUseCase getFeaturedProductListUseCase;
    private final GetProductByNameUseCase findProductByNameUseCase;
    private final GetProductDetailByIdUseCase findProductDetailByIdUseCase;
    private final CreateProductUseCase createProductUseCase;

    @Operation(summary = "Get featured products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found featured product", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping(path = "/featured")
    public Mono<List<Product>> getFeaturedProductList() {
        return getFeaturedProductListUseCase.execute().collectList();
    }

    @Operation(summary = "Get product by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product by name", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PaginationProduct.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping()
    public Mono<PaginationProduct> getProductByName(
            @RequestParam() String name,
            @RequestParam() int page,
            @RequestParam() int size) {

        Map<String, Object> params = new HashMap<>();
        params.put(NameParamsFindByNameEnum.NAME.getParam(), name);
        params.put(NameParamsFindByNameEnum.PAGE.getParam(), page);
        params.put(NameParamsFindByNameEnum.SIZE.getParam(), size);

        return findProductByNameUseCase.execute(params);
    }

    @Operation(summary = "Get product with description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product with description", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDetail.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping(path = "/detail")
    public Mono<ProductDetail> getProductDetail(@RequestParam() String id) {
        return findProductDetailByIdUseCase.execute(id);
    }

    @Operation(summary = "Create product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CompleteProductDto.class))}),
            @ApiResponse(responseCode = "400", description = "The discount price is wrong", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PostMapping()
    public Mono<CompleteProductDto> createProduct(@RequestBody() CompleteProductDto completeProductDto) {
        return createProductUseCase.execute(ProductFactory.convertToCompleteProduct(completeProductDto))
                .map(ProductFactory::convertToCompleteProductDto)
                .onErrorResume(error -> {
                    if (error instanceof ProductException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage(), error));
                    }
                    return Mono.error(error);
                });
    }

}
