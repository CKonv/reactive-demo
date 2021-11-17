package de.ckonv.reactivedemo.reactiveclient;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactiveClientApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
        SpringApplication.run(ReactiveClientApplication.class, args);
    GreetingClient greetingClient = context.getBean(GreetingClient.class);

    System.out.println();
    System.out.println("##### GET MONO #####");
    System.out.println(">> message = " + greetingClient.getMono().block());
    System.out.println();

    System.out.println("##### GET FLUX #####");
    greetingClient
        .getFlux()
        .doOnNext(message -> System.out.println(">> message = " + message))
        .blockLast();
    System.out.println();

    System.out.println("##### POST MONO GET MONO #####");
    System.out.println(">> message = " + greetingClient.postMonoGetMono("TestName").block());
    System.out.println();

    System.out.println("##### POST FLUX GET MONO #####");
    System.out.println(
        ">> message = "
            + greetingClient.postFluxGetMono(List.of("TestName1", "TestName2")).block());
    System.out.println();

    System.out.println("##### POST FLUX GET FLUX #####");
    greetingClient
        .postFluxGetFlux(List.of("TestName1", "TestName2"))
        .doOnNext(message -> System.out.println(">> message = " + message))
        .blockLast();
  }
}
