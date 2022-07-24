package ro.fasttrackit.curs10homework.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs10homework.exception.ResourceNotFound;
import ro.fasttrackit.curs10homework.model.TrainEntity;
import ro.fasttrackit.curs10homework.model.filters.TrainFilter;
import ro.fasttrackit.curs10homework.repository.LocationRepository;
import ro.fasttrackit.curs10homework.repository.TrainDAO;

@Component
@RequiredArgsConstructor
public class TrainService {
    private final TrainDAO dao;
    private final LocationRepository locationRepository;

    public Page<TrainEntity> getAll(TrainFilter filter, Pageable pageable) {
        return dao.findAllWithFilter(filter, pageable);
    }

    public TrainEntity add(TrainEntity entity) {
        locationRepository.findById(entity.getLocationId())
                .orElseThrow(() -> new ResourceNotFound("Location with id: %s was not found".formatted(entity.getLocationId())));

        return dao.save(entity);
    }

    public TrainEntity updateEntity(String id, JsonPatch jsonPatch) {
        return dao.findById(id)
                .map(dbEntity -> applyPatch(dbEntity, jsonPatch))
                .map(dbEntity -> replace(id, dbEntity))
                .orElseThrow(() -> new ResourceNotFound("Couldn't find train with id %s".formatted(id)));
    }

    public TrainEntity replace(String id, TrainEntity newEntity) {
        TrainEntity dbEntity = dao.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Couldn't find train with id %s".formatted(id)));
        return dao.save(
                dbEntity.withCarts(newEntity.getCarts())
                        .withLocationId(newEntity.getLocationId()));
    }

    private TrainEntity applyPatch(TrainEntity dbEntity, JsonPatch jsonPatch) {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode jsonNode = jsonMapper.convertValue(dbEntity, JsonNode.class);
            JsonNode patchedJson = jsonPatch.apply(jsonNode);
            return jsonMapper.treeToValue(patchedJson, TrainEntity.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public TrainEntity delete(String id) {
        TrainEntity dbEntity = dao.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Couldn't find train with id %s".formatted(id)));
        dao.deleteById(id);
        return dbEntity;
    }
}
