package ro.fasttrackit.curs10homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs10homework.controller.mappers.LocationMappers;
import ro.fasttrackit.curs10homework.model.api.Location;
import ro.fasttrackit.curs10homework.service.LocationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("locations")
public class LocationController {
    private final LocationService service;
    private final LocationMappers mappers;

    @PostMapping
    public Location add(@RequestBody Location newEntity) {
        return mappers.toApi(service.add(mappers.toDb(newEntity)));
    }

    @DeleteMapping("{id}")
    public Location add(@PathVariable String id) {
        return mappers.toApi(service.delete(id));
    }
}
