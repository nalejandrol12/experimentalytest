package co.example.controllers.factory;

import co.example.controllers.dto.CompleteProductDto;
import co.example.entities.CompleteProduct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductFactory {
    public static CompleteProductDto convertToCompleteProductDto(CompleteProduct completeProduct) {
        return completeProduct != null
                ? CompleteProductDto.builder()
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

    public static CompleteProduct convertToCompleteProduct(CompleteProductDto completeProductDto) {
        return completeProductDto != null
                ? CompleteProduct.builder()
                .id(completeProductDto.getId())
                .name(completeProductDto.getName())
                .price(completeProductDto.getPrice())
                .priceDiscount(completeProductDto.getPriceDiscount())
                .discountRate(completeProductDto.getDiscountRate())
                .description(completeProductDto.getDescription())
                .frontImage(completeProductDto.getFrontImage())
                .backImage(completeProductDto.getBackImage())
                .otherImages(completeProductDto.getOtherImages())
                .featuredProduct(completeProductDto.isFeaturedProduct())
                .build()
                : null;
    }
}
