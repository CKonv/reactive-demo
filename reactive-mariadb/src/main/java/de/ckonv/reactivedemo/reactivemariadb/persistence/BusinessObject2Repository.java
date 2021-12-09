package de.ckonv.reactivedemo.reactivemariadb.persistence;

import de.ckonv.reactivedemo.reactivemariadb.domain.model.BusinessObject2;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BusinessObject2Repository
    extends ReactiveCrudRepository<BusinessObject2, Integer> {

  @Query(value = "SELECT * FROM business_object_2 WHERE nameKey = :nameKey")
  Mono<BusinessObject2> findFirstByNameKey(String nameKey);
}
