package co.example.mongodb.builder;

import co.example.entities.CompleteProduct;
import co.example.entities.Product;
import co.example.entities.ProductDetail;
import co.example.mongodb.data.CompleteProductData;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductBuilder {
    public static Product convertToProduct(CompleteProductData productData) {
        return productData != null ?
                Product.builder()
                        .id(productData.getId())
                        .name(productData.getName())
                        .price(productData.getPrice())
                        .priceDiscount(productData.getPriceDiscount())
                        .discountRate(productData.getDiscountRate())
                        .frontImage(productData.getFrontImage())
                        .backImage(productData.getBackImage())
                        .build()
                : null;
    }

    public static List<Product> convertToProductList(List<CompleteProductData> productDataList) {
        return productDataList != null ?
                productDataList.stream().map(ProductBuilder::convertToProduct)
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }

    public static CompleteProduct convertToCompleteProduct(CompleteProductData completeProductData) {
        return completeProductData != null
                ? CompleteProduct.builder()
                .id(completeProductData.getId())
                .name(completeProductData.getName())
                .price(completeProductData.getPrice())
                .priceDiscount(completeProductData.getPriceDiscount())
                .discountRate(completeProductData.getDiscountRate())
                .description(completeProductData.getDescription())
                .frontImage(completeProductData.getFrontImage())
                .backImage(completeProductData.getBackImage())
                .otherImages(completeProductData.getOtherImages())
                .featuredProduct(completeProductData.isFeaturedProduct())
                .build()
                : null;
    }

    public static CompleteProductData convertToCompleteProductData(CompleteProduct completeProduct) {
        return completeProduct != null
                ? CompleteProductData.builder()
                .id(completeProduct.getId())
                .name(completeProduct.getName())
                .price(completeProduct.getPrice())
                .priceDiscount(completeProduct.getPriceDiscount())
                .discountRate(completeProduct.getDiscountRate())
                .description(completeProduct.getDescription())
                .frontImage(completeProduct.getFrontImage())
                .backImage(completeProduct.getBackImage())
                .otherImages(completeProduct.getOtherImages())
                .featuredProduct(completeProduct.isFeaturedProduct())
                .build()
                : null;
    }

    public static ProductDetail convertToProductDetail(CompleteProductData productData) {
        return productData != null
                ? ProductDetail.builder()
                .id(productData.getId())
                .name(productData.getName())
                .price(productData.getPrice())
                .priceDiscount(productData.getPriceDiscount())
                .discountRate(productData.getDiscountRate())
                .description(productData.getDescription())
                .images(newImageList(productData))
                .build()
                : null;
    }

    private static List<String> newImageList(CompleteProductData productData) {

        List<String> images = new ArrayList<>();
        images.add(productData.getBackImage());
        images.add(productData.getFrontImage());
        images.addAll(productData.getOtherImages());

        return images;
    }
}
