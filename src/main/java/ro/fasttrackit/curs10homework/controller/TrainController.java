package ro.fasttrackit.curs10homework.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs10homework.controller.mappers.TrainMappers;
import ro.fasttrackit.curs10homework.model.PageInfo;
import ro.fasttrackit.curs10homework.model.TrainEntity;
import ro.fasttrackit.curs10homework.model.api.Train;
import ro.fasttrackit.curs10homework.model.filters.TrainFilter;
import ro.fasttrackit.curs10homework.service.TrainService;

@RestController
@RequiredArgsConstructor
@RequestMapping("trains")
public class TrainController {
    private final TrainService service;
    private final TrainMappers mappers;

    @GetMapping
    public PageInfo<Train> getAll(TrainFilter filter, Pageable pageable) {
        Page<TrainEntity> page = service.getAll(filter, pageable);
        return PageInfo.<Train>builder()
                .currentPage(page.getPageable().getPageNumber())
                .pageSize(page.getSize())
                .totalItems(page.getTotalElements())
                .items(page.getContent().stream()
                        .map(mappers::toApi)
                        .toList())
                .build();
    }

    @PostMapping
    public Train add(@RequestBody Train newEntity) {
        return mappers.toApi(service.add(mappers.toDb(newEntity)));
    }

    @PatchMapping("{id}")
    public Train patchEntity(@PathVariable String id, @RequestBody JsonPatch jsonPatch) {
        return mappers.toApi(service.updateEntity(id, jsonPatch));
    }


    @DeleteMapping("{id}")
    public Train deleteEntity(@PathVariable String id) {
        return mappers.toApi(service.delete(id));
    }
}
