package ru.bojark.conditionalapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalAppApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);


    @BeforeAll
    static void setUp() {
        prodapp.start();
        devapp.start();
    }

    @Test
    void contextLoadsDev() {
        String assertion = "Current profile is dev";
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:"
                + devapp.getMappedPort(8080) + "/profile", String.class);
        System.out.println(forEntity.getBody());
        assertEquals(forEntity.getBody(), assertion);

    }

    @Test
    void contextLoadsProd() {
        String assertion = "Current profile is production";
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:"
                + prodapp.getMappedPort(8081) + "/profile", String.class);
        System.out.println(forEntity.getBody());
        assertEquals(forEntity.getBody(), assertion);
    }


}
