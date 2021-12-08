package de.ckonv.reactivedemo.reactivemariadb.domain.service;

import de.ckonv.reactivedemo.reactivemariadb.domain.model.Object1xObject2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ObjectService {

  public Mono<String> saveDtos(Flux<Object1xObject2> dtos) {

    return toSizeString(dtos);
  }

  private Mono<String> toSizeString(Flux<?> stream) {
    return stream.collectList().flatMap(list -> Mono.just("Found: " + list.size()));
  }
}
