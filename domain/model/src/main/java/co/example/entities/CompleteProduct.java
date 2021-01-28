package co.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class CompleteProduct {
    private final String id;
    private final String name;
    private final Double price;
    private final Double priceDiscount;
    private final Double discountRate;
    private final String description;
    private final String frontImage;
    private final String backImage;
    private final List<String> otherImages;
    private final boolean featuredProduct;
}
