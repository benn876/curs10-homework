package ro.fasttrackit.curs10homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs10homework.model.TrainEntity;

@Repository
public interface TrainRepository extends MongoRepository<TrainEntity,String> {
}
