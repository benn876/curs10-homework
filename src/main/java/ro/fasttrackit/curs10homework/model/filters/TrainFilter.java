package ro.fasttrackit.curs10homework.model.filters;

import lombok.Builder;

@Builder
public record TrainFilter(
        String locationId,
        String model,
        Integer minCarts,
        Integer maxCarts
) {
}
