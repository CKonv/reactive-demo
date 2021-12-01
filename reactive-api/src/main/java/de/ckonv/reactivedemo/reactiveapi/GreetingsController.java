package de.ckonv.reactivedemo.reactiveapi;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class GreetingsController {

  @GetMapping(value = "/get_mono")
  private Mono<Greeting> getGreeting() {
    return Mono.just(new Greeting("Hello from Mono!"));
  }

  @GetMapping(value = "/get_flux")
  private Flux<Greeting> getGreetings() {
    return Flux.fromIterable(
        List.of(new Greeting("First Hello from Flux!"), new Greeting("Second Hello from Flux!")));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/post_mono_get_mono")
  public Mono<Greeting> getGreetingFromMono(@RequestBody Mono<Name> name) {
    return name.map(n -> new Greeting("Hello, " + n.getName() + "!"));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/post_flux_get_mono")
  public Mono<Greeting> getGreetingFromFlux(@RequestBody Flux<Name> names) {
    return names
        .onErrorResume(e -> System.out::println)
        .doOnEach(System.out::println)
        .collectList()
        .flatMap(
            nameList ->
                Mono.just(new Greeting("Received " + nameList.size() + " elements in Flux")));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/post_flux_get_flux")
  public Flux<Greeting> getGreetingsFromFlux(@RequestBody Flux<Name> names) {
    return names
        .doOnEach(System.out::println)
        .map(n -> new Greeting("Hello, " + n.getName() + "!"));
  }
}
