package co.example.controllers;

import co.example.controllers.dto.CompleteProductDto;
import co.example.controllers.factory.ProductFactory;
import co.example.entities.PaginationProduct;
import co.example.entities.Product;
import co.example.entities.ProductDetail;
import co.example.enums.NameParamsFindByNameEnum;
import co.example.enums.StatusCodeEnum;
import co.example.usecase.CreateProductUseCase;
import co.example.usecase.GetFeaturedProductListUseCase;
import co.example.usecase.GetProductByNameUseCase;
import co.example.usecase.GetProductDetailByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final GetFeaturedProductListUseCase getFeaturedProductListUseCase;
    private final GetProductByNameUseCase findProductByNameUseCase;
    private final GetProductDetailByIdUseCase findProductDetailByIdUseCase;
    private final CreateProductUseCase createProductUseCase;

    @GetMapping(path = "/featured")
    public Mono<List<Product>> getFeaturedProductList() {
        return getFeaturedProductListUseCase.execute().collectList();
    }

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

    @GetMapping(path = "/detail")
    public Mono<ProductDetail> getProductDetail(@RequestParam() String id) {
        return findProductDetailByIdUseCase.execute(id);
    }

    @PostMapping()
    public Mono<ResponseEntity> createProduct(@RequestBody() CompleteProductDto completeProductDto) {
        return createProductUseCase.execute(ProductFactory.convertToCompleteProduct(completeProductDto))
                .map(tuple -> tuple.getT2() == StatusCodeEnum.CREATE.getCode()
                        ? new ResponseEntity<>(ProductFactory.convertToCompleteProductDto(tuple.getT1()), HttpStatus.CREATED)
                        : new ResponseEntity<>(tuple.getT3(), HttpStatus.BAD_REQUEST));
    }

}
