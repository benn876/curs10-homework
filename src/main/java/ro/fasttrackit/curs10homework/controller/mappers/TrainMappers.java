package ro.fasttrackit.curs10homework.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.fasttrackit.curs10homework.model.TrainEntity;
import ro.fasttrackit.curs10homework.model.api.Train;

@Mapper(componentModel = "spring")
public interface TrainMappers {
    Train toApi(TrainEntity trainEntity);
    TrainEntity toDb(Train train);
}
