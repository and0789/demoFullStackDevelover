package com.itc.demofullstack;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public abstract class AbstractTestcontainers {

    @Container
    protected static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("andreseptian-dao-unit-test")
                    .withUsername("andreseptian")
                    .withPassword("123456");

    @BeforeAll
    static void beforeAll() {
        Flyway flyway = Flyway.configure().dataSource(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        ).load();
        flyway.migrate();
        System.out.println();
    }

    @DynamicPropertySource
    private static void registerDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.datasource.url",
                () -> postgreSQLContainer.getJdbcUrl()
        );
        registry.add(
                "spring.datasource.username",
                () -> postgreSQLContainer.getUsername()
        );
        registry.add(
                "spring.datasource.password",
                () -> postgreSQLContainer.getPassword()
        );
    }

//    @Test
//    void canStartPostgresDB() {
//        assertThat(postgreSQLContainer.isRunning()).isTrue();
//        assertThat(postgreSQLContainer.isCreated()).isTrue();
//    }

//    @Test
//    void canApplyDbMigrationsWithFlyway() {
//        Flyway flyway = Flyway.configure().dataSource(
//                postgreSQLContainer.getJdbcUrl(),
//                postgreSQLContainer.getUsername(),
//                postgreSQLContainer.getPassword()
//        ).load();
//        flyway.migrate();
//        System.out.println();
//    }
}
