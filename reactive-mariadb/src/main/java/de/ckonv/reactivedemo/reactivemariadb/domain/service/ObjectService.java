package de.ckonv.reactivedemo.reactivemariadb.domain.service;

import de.ckonv.reactivedemo.reactivemariadb.domain.model.BusinessObject1XBusinessObject2;
import de.ckonv.reactivedemo.reactivemariadb.domain.model.Object1xObject2;
import de.ckonv.reactivedemo.reactivemariadb.persistence.BusinessObject1XBusinessObject2Repository;
import de.ckonv.reactivedemo.reactivemariadb.persistence.BusinessObject2Repository;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class ObjectService {

  private final BusinessObject2Repository businessObject2Repository;

  private final BusinessObject1XBusinessObject2Repository businessObject1XBusinessObject2Repository;

  public Flux<BusinessObject1XBusinessObject2> saveMappingDtos(Flux<Object1xObject2> dtos) {

    var saved =
        dtos.log()
            .mapNotNull(
                dto -> {
                  if (!dto.getBusinessObject2NameKeys().isEmpty()) {
                    return dto.getBusinessObject2NameKeys().stream()
                        .collect(Collectors.toMap(e -> e, e -> dto.getBusinessObject1Id()));
                  }
                  return null;
                })
            .map(Map::entrySet)
            .flatMap(Flux::fromIterable)
            .flatMap(
                e -> {
                  log.info("e: " + e.toString());
                  return businessObject2Repository
                      .findFirstByNameKey(e.getKey())
                      .map(
                          a -> {
                            log.info("a: " + a.toString());
                            return Map.entry(a.getId(), e.getValue());
                          });
                })
            .map(
                e ->
                    BusinessObject1XBusinessObject2.builder()
                        .o1_id(e.getValue())
                        .o2_id(e.getKey())
                        .build())
            .flatMap(businessObject1XBusinessObject2Repository::save);

    return businessObject1XBusinessObject2Repository.deleteAll().thenMany(saved);
  }
}
