package ro.fasttrackit.curs10homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs10homework.exception.ResourceNotFound;
import ro.fasttrackit.curs10homework.model.LocationEntity;
import ro.fasttrackit.curs10homework.repository.LocationRepository;

@Component
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository repository;

    public LocationEntity add(LocationEntity entity) {
        return repository.save(entity);
    }

    public LocationEntity delete(String id) {
        LocationEntity location = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Location with id: %s was not found".formatted(id)));
        repository.deleteById(id);
        return location;
    }
}
