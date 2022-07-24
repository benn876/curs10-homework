package ro.fasttrackit.curs10homework.model.api;

import lombok.Builder;

@Builder
public record Train(
        String id,
        String model,
        String carts,
        String locationId) {
}
