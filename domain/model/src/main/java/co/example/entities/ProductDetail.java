package co.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ProductDetail {
    private final String id;
    private final String name;
    private final Double price;
    private final Double priceDiscount;
    private final Double discountRate;
    private final String description;
    private final List<String> images;
}
