package de.ckonv.reactivedemo.reactivemariadb.api.controller;

import de.ckonv.reactivedemo.reactivemariadb.api.dto.Object1xObject2Dto;
import de.ckonv.reactivedemo.reactivemariadb.domain.model.BusinessObject1XBusinessObject2;
import de.ckonv.reactivedemo.reactivemariadb.domain.model.Object1xObject2;
import de.ckonv.reactivedemo.reactivemariadb.domain.service.ObjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BusinessObjectController {

  private final ObjectService objectService;

  private final ConversionService conversionService;

  @PostMapping(
      path = "/mappings",
      consumes = MediaType.APPLICATION_NDJSON_VALUE,
      produces = MediaType.APPLICATION_NDJSON_VALUE)
  ResponseEntity<Flux<BusinessObject1XBusinessObject2>> saveMappings(
      @RequestBody Flux<Object1xObject2Dto> mappings) {

    var conversion =
        mappings.mapNotNull(obj -> conversionService.convert(obj, Object1xObject2.class));

    return ResponseEntity.ok(objectService.saveMappingDtos(conversion).log());
  }
}
