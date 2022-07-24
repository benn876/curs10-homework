package ro.fasttrackit.curs10homework.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "trains")
@With
public class TrainEntity {
    @MongoId
    private String id = randomUUID().toString();
    private String model;
    private String carts;
    private String locationId;
}
