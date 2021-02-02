package co.example.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class CompleteProductDto {
    private String id;
    private String name;
    private Double price;
    private Double priceDiscount;
    private Double discountRate;
    private String description;
    private String frontImage;
    private String backImage;
    private List<String> otherImages;
    private boolean featuredProduct;
}
