package co.example.mongodb.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ImageInformationData {
    private String type;
    private String url;
}
