# Reactive mariadb demo project

## info
This project uses flyway for database migrations. Since flyway does not support the R2DBC drivers it needs its own JDBC driver:
1. Add the following dependencies:
   ```xml
   <dependency>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-core</artifactId>
   </dependency>
   <dependency>
      <groupId>org.mariadb.jdbc</groupId>
      <artifactId>mariadb-java-client</artifactId>
      <scope>runtime</scope>
   </dependency>
   ```
2. Add flyway variable to properties file:
   ```
    spring.flyway.url      = jdbc:mariadb://...
    spring.flyway.user     = ...
    spring.flyway.password = ...
   ```
3. Add configuration to project:
   ```java
    @Configuration
    public class FlywayConfig {
    
      private final Environment env;
    
      public FlywayConfig(final Environment env) {
        this.env = env;
      }
    
      @Bean(initMethod = "migrate")
      public Flyway flyway() {
        return new Flyway(
          Flyway.configure()
            .baselineOnMigrate(true)
            .dataSource(
              env.getRequiredProperty("spring.flyway.url"),
              env.getRequiredProperty("spring.flyway.user"),
              env.getRequiredProperty("spring.flyway.password")));
      }
    }
   ```