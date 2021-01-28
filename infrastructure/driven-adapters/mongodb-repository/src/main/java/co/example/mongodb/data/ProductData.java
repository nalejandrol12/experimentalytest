package co.example.mongodb.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Products")
@Builder(toBuilder = true)
public class ProductData {
    @Id
    private String id;
    private String productName;
    private Double price;
    private Double priceDiscount;
    private Double discountRate;
    private String description;
    private String frontImage;
    private String backImage;
    private List<String> otherImages;
    private boolean featuredProduct;
}
