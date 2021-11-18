package de.ckonv.reactivedemo.reactiveclient;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GreetingClient {

  private final WebClient client;

  public GreetingClient(WebClient.Builder builder) {
    this.client = builder.baseUrl("http://localhost:8080").build();
  }

  public Mono<String> getMono() {
    return this.client
        .get()
        .uri("/get_mono")
        .retrieve()
        .bodyToMono(Greeting.class)
        .map(Greeting::getMessage);
  }

  public Flux<String> getFlux() {
    return this.client
        .get()
        .uri("/get_flux")
        .retrieve()
        .bodyToFlux(Greeting.class)
        .map(Greeting::getMessage);
  }

  public Mono<String> postMonoGetMono(String name) {
    return this.client
        .post()
        .uri("/post_mono_get_mono")
        .body(Mono.just(new Name(name)), Name.class)
        .retrieve()
        .bodyToMono(Greeting.class)
        .map(Greeting::getMessage);
  }

  public Mono<String> postFluxGetMono(List<String> names) {
    return this.client
        .post()
        .uri("/post_flux_get_mono")
        .body(Flux.fromIterable(names), Name.class)
        .retrieve()
        .bodyToMono(Greeting.class)
        .map(Greeting::getMessage);
  }

  public Flux<String> postFluxGetFlux(List<String> names) {
    return this.client
        .post()
        .uri("/post_flux_get_flux")
        .body(Flux.fromIterable(names), Name.class)
        .retrieve()
        .bodyToFlux(Greeting.class)
        .map(Greeting::getMessage);
  }
}
