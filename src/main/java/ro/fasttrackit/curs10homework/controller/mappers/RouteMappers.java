package ro.fasttrackit.curs10homework.controller.mappers;

import org.mapstruct.Mapper;
import ro.fasttrackit.curs10homework.model.RouteEntity;
import ro.fasttrackit.curs10homework.model.api.Route;

@Mapper(componentModel = "spring")
public interface RouteMappers {
    Route toApi(RouteEntity trainEntity);

    RouteEntity toDb(Route train);
}
