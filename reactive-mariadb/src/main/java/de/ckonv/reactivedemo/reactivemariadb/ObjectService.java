package de.ckonv.reactivedemo.reactivemariadb;

import de.ckonv.reactivedemo.reactivemariadb.persistance.DTOEntity;
import de.ckonv.reactivedemo.reactivemariadb.persistance.DTORepository;
import de.ckonv.reactivedemo.reactivemariadb.persistance.Object1_x_object2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ObjectService {

  private final Object1_x_object2Repository o1o2repository;
  private final DTORepository dtoRepository;

  public Mono<String> saveDtos(Flux<ObjectDTO> dtos) {

    var dto_mapping =
        dtos.doOnEach(d -> System.out.println("DTO: " + d))
            .map(
                d ->
                    DTOEntity.builder()
                        .o1_id(d.getO1_id())
                        .name_keys(d.getName_keys().toString())
                        .build())
            .doOnEach(d -> System.out.println("DTOEntity: " + d));

    return dtoRepository
        .deleteAll()
        .thenMany(dtoRepository.saveAll(dto_mapping))
        .collectList()
        .flatMap(list -> Mono.just("Saved: " + list.size()));
  }
}
