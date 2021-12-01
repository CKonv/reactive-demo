package de.ckonv.reactivedemo.reactivemariadb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final ObjectService objectService;

  @PostMapping("/post_flux_get_mono")
  public Mono<String> receive_mappings(@RequestBody Flux<ObjectDTO> mappings) {
    return objectService.saveDtos(mappings);
  }
}
