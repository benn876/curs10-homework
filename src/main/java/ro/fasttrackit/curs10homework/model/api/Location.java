package ro.fasttrackit.curs10homework.model.api;

import lombok.Builder;

@Builder
public record Location(
        String id,
        String city) {
}
