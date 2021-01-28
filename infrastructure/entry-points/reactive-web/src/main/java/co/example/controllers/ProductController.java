package co.example.controllers;

import co.example.entities.ProductDetail;
import co.example.enums.NameParamsFindByNameEnum;
import co.example.entities.PaginationProduct;
import co.example.entities.Product;
import co.example.usecase.FindProductByNameUseCase;
import co.example.usecase.FindProductDetailByIdUseCase;
import co.example.usecase.GetFeaturedProductListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final GetFeaturedProductListUseCase getFeaturedProductListUseCase;
    private final FindProductByNameUseCase findProductByNameUseCase;
    private final FindProductDetailByIdUseCase findProductDetailByIdUseCase;

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

}
