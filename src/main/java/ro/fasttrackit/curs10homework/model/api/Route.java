package ro.fasttrackit.curs10homework.model.api;

import lombok.Builder;

@Builder
public record Route(
        String id,
        String startLocationId,
        String destinationLocationId,
        String length,
        String trainId) {
}
