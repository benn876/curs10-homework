package ro.fasttrackit.curs10homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.curs10homework.controller.mappers.RouteMappers;
import ro.fasttrackit.curs10homework.model.api.Location;
import ro.fasttrackit.curs10homework.model.api.Route;
import ro.fasttrackit.curs10homework.service.RouteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("routes")
public class RouteController {
    private final RouteService service;
    private final RouteMappers mappers;

    @PostMapping
    public Route add(@RequestBody Route newEntity) {
        return mappers.toApi(service.add(mappers.toDb(newEntity)));
    }
}
