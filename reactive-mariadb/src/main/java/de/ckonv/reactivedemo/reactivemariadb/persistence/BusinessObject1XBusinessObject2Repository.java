package de.ckonv.reactivedemo.reactivemariadb.persistence;

import de.ckonv.reactivedemo.reactivemariadb.domain.model.BusinessObject1XBusinessObject2;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BusinessObject1XBusinessObject2Repository
    extends ReactiveCrudRepository<BusinessObject1XBusinessObject2, Integer> {

  @Query("DELETE FROM test.object1_x_object2 WHERE o1_id = :o1_id")
  Mono<Integer> deleteAllByO1_id(Integer o1_id);

  @Query(
      "INSERT INTO test.object1_x_object2 (o1_id, o2_id) VALUES (:o1_id, (SELECT id FROM test.object2 WHERE name_key = :name_key));")
  Mono<Integer> insertCustom(Integer o1_id, String name_key);
}
