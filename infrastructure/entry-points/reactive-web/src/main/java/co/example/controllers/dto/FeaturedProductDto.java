package co.example.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class FeaturedProductDto {
    @JsonProperty("nombreProducto")
    private String productName;
    @JsonProperty("precio")
    private Double price;
    @JsonProperty("precioDescuento")
    private Double priceDiscount;
    @JsonProperty("porcentajeDescuento")
    private Double discountRate;
    @JsonProperty("imagenFrontal")
    private String frontImage;
    @JsonProperty("imagenTrasera")
    private String backImage;
}
