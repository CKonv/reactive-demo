package de.ckonv.reactivedemo.reactivemariadb.domain.service;

import de.ckonv.reactivedemo.reactivemariadb.domain.model.BusinessObject1XBusinessObject2;
import de.ckonv.reactivedemo.reactivemariadb.domain.model.Object1xObject2;
import de.ckonv.reactivedemo.reactivemariadb.persistence.BusinessObject1XBusinessObject2Repository;
import de.ckonv.reactivedemo.reactivemariadb.persistence.BusinessObject2Repository;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
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
        dtos.mapNotNull(getO1IdKeysMappingFunction())
            .map(Map::entrySet)
            .flatMap(Flux::fromIterable)
            .flatMap(mapMappingStringToIdFunction())
            .map(getO1xO2EntityFromMappingFunction())
            .flatMap(businessObject1XBusinessObject2Repository::save);

    return businessObject1XBusinessObject2Repository.deleteAll().thenMany(saved);
  }

  private Function<Entry<Integer, Integer>, BusinessObject1XBusinessObject2>
      getO1xO2EntityFromMappingFunction() {
    return mapping ->
        BusinessObject1XBusinessObject2.builder()
            .o1_id(mapping.getValue())
            .o2_id(mapping.getKey())
            .build();
  }

  private Function<Entry<String, @NonNull Integer>, Publisher<? extends Entry<Integer, Integer>>>
      mapMappingStringToIdFunction() {
    return mapEntry ->
        businessObject2Repository
            .findFirstByNameKey(mapEntry.getKey())
            .map(businessObject2 -> Map.entry(businessObject2.getId(), mapEntry.getValue()));
  }

  private Function<Object1xObject2, Map<String, @NonNull Integer>> getO1IdKeysMappingFunction() {
    return mapping ->
        mapping.getBusinessObject2NameKeys().stream()
            .collect(Collectors.toMap(key -> key, key -> mapping.getBusinessObject1Id()));
  }
}
