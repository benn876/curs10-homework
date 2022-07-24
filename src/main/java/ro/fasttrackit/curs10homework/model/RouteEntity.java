package ro.fasttrackit.curs10homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import static java.util.UUID.randomUUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "routes")
public class RouteEntity {
    @MongoId
    private String id = randomUUID().toString();
    private String startLocationId;
    private String destinationLocationId;
    private Long length;
    private String trainId;
}
