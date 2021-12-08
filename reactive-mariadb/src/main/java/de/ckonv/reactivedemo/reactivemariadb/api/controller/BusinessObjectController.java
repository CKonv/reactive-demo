package de.ckonv.reactivedemo.reactivemariadb.api.controller;

import de.ckonv.reactivedemo.reactivemariadb.api.dto.Object1xObject2Dto;
import de.ckonv.reactivedemo.reactivemariadb.domain.model.Object1xObject2;
import de.ckonv.reactivedemo.reactivemariadb.domain.service.ObjectService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BusinessObjectController {

  private final ObjectService objectService;

  private final ConversionService conversionService;

  @PostMapping(path = "/post_flux_get_mono", consumes = MediaType.APPLICATION_NDJSON_VALUE)
  ResponseEntity<?> receive_mappings(@RequestBody Flux<Object1xObject2Dto> mappings) {

    var conversion =
        mappings.mapNotNull(obj -> conversionService.convert(obj, Object1xObject2.class));

    return ResponseEntity.ok(
        objectService.saveDtos(conversion).delayUntil(d -> Mono.delay(Duration.ofSeconds(3))));
  }
}
