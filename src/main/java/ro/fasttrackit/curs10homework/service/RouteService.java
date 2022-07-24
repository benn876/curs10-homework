package ro.fasttrackit.curs10homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs10homework.exception.ResourceNotFound;
import ro.fasttrackit.curs10homework.exception.ValidationException;
import ro.fasttrackit.curs10homework.model.RouteEntity;
import ro.fasttrackit.curs10homework.model.TrainEntity;
import ro.fasttrackit.curs10homework.repository.LocationRepository;
import ro.fasttrackit.curs10homework.repository.RouteRepository;
import ro.fasttrackit.curs10homework.repository.TrainDAO;

@Component
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository repository;
    private final TrainDAO trainDAO;
    private final LocationRepository locationRepository;

    public RouteEntity add(RouteEntity entity) {
        validateData(entity);
        return repository.save(entity);
    }

    private void validateData(RouteEntity entity) {
        validateLocation(entity);
        validateTrain(entity);
    }

    private void validateTrain(RouteEntity entity) {
        TrainEntity trainEntity = trainDAO.findById(entity.getTrainId())
                .orElseThrow(() -> new ResourceNotFound("Train with id: %s was not found".formatted(entity.getTrainId())));

        if (!trainEntity.getLocationId().equals(entity.getStartLocationId())) {
            throw new ValidationException("The train is not at the start location");
        }
    }

    private void validateLocation(RouteEntity entity) {
        locationRepository.findById(entity.getStartLocationId())
                .orElseThrow(() -> new ResourceNotFound("Location with id: %s was not found".formatted(entity.getStartLocationId())));
        locationRepository.findById(entity.getDestinationLocationId())
                .orElseThrow(() -> new ResourceNotFound("Location with id: %s was not found".formatted(entity.getDestinationLocationId())));
    }
}
