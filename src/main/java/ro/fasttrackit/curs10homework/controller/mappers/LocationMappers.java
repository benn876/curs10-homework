package ro.fasttrackit.curs10homework.controller.mappers;

import org.mapstruct.Mapper;
import ro.fasttrackit.curs10homework.model.LocationEntity;
import ro.fasttrackit.curs10homework.model.TrainEntity;
import ro.fasttrackit.curs10homework.model.api.Location;
import ro.fasttrackit.curs10homework.model.api.Train;

@Mapper(componentModel = "spring")
public interface LocationMappers {
    Location toApi(LocationEntity trainEntity);
    LocationEntity toDb(Location train);
}
