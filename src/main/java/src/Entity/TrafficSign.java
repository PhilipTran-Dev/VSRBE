package src.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trafficsign")
@Data
@Getter
@Setter
public class TrafficSign {

    @Id
    private String id;

    private String signName;
    private String signCode;
    private String description;
    private String image;
}
