package co.example.mongodb.builder;

import co.example.entities.Product;
import co.example.entities.ProductDetail;
import co.example.mongodb.data.ProductData;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductBuilder {
    public static Product convertToProduct(ProductData productData) {
        return productData != null ?
                Product.builder()
                        .id(productData.getId())
                        .productName(productData.getProductName())
                        .price(productData.getPrice())
                        .priceDiscount(productData.getPriceDiscount())
                        .discountRate(productData.getDiscountRate())
                        .frontImage(productData.getFrontImage())
                        .backImage(productData.getBackImage())
                        .build()
                : null;
    }

    public static List<Product> convertToProductList(List<ProductData> productDataList) {
        return productDataList != null ?
                productDataList.stream().map(ProductBuilder::convertToProduct)
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }

    public static ProductDetail convertToProductDetail(ProductData productData) {
        return productData != null
                ? ProductDetail.builder()
                .id(productData.getId())
                .productName(productData.getProductName())
                .price(productData.getPrice())
                .priceDiscount(productData.getPriceDiscount())
                .discountRate(productData.getDiscountRate())
                .description(productData.getDescription())
                .images(newImageList(productData))
                .build()
                : null;
    }

    private static List<String> newImageList(ProductData productData) {

        List<String> images = new ArrayList<>();
        images.add(productData.getBackImage());
        images.add(productData.getFrontImage());
        images.addAll(productData.getOtherImages());

        return images;
    }
}
