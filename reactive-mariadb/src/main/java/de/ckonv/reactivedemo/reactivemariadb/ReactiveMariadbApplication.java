package de.ckonv.reactivedemo.reactivemariadb;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ReactiveMariadbApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveMariadbApplication.class, args);
  }

  @Bean(initMethod = "migrate")
  public Flyway flyway(Environment env) {
    return new Flyway(
        Flyway.configure()
            .dataSource(
                env.getRequiredProperty("spring.flyway.url"),
                env.getRequiredProperty("spring.flyway.user"),
                env.getRequiredProperty("spring.flyway.password")));
  }
}
