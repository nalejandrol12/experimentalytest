package co.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Product {
    private final String id;
    private final String productName;
    private final Double price;
    private final Double priceDiscount;
    private final Double discountRate;
    private final String frontImage;
    private final String backImage;
}
