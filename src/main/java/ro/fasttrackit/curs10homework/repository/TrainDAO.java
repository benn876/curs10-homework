package ro.fasttrackit.curs10homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs10homework.model.TrainEntity;
import ro.fasttrackit.curs10homework.model.filters.TrainFilter;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

@Repository
@RequiredArgsConstructor
public class TrainDAO {
    private final TrainRepository repository;
    private final MongoTemplate mongoTemplate;

    public Page<TrainEntity> findAllWithFilter(TrainFilter filters, Pageable pageable) {
        Criteria criteria = new Criteria();
        ofNullable(filters.locationId())
                .ifPresent(locationId -> criteria.and("locationId").is(locationId));
        ofNullable(filters.model())
                .ifPresent(model -> criteria.and("model").is(model));
        ofNullable(filters.minCarts())
                .ifPresent(minCarts -> criteria.and("carts").gte(minCarts));
        ofNullable(filters.maxCarts())
                .ifPresent(maxCarts -> criteria.and("carts").lte(maxCarts));
        Query query = Query.query(criteria).with(pageable);
        List<TrainEntity> content = mongoTemplate.find(query, TrainEntity.class);
        return getPage(content, pageable,
                () -> mongoTemplate.count(Query.query(criteria), TrainEntity.class));
    }

    public TrainEntity save(TrainEntity trainEntity) {
        return repository.save(trainEntity);
    }

    public Optional<TrainEntity> findById(String id){
        return repository.findById(id);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }
}
