package ro.fasttrackit.curs10homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "locations")
public class LocationEntity {
    @MongoId
    private String id = randomUUID().toString();
    private String city;
}
