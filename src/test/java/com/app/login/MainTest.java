
package com.app.login;

import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.helidon.microprofile.tests.junit5.HelidonTest;

@HelidonTest
class MainTest {

    @Inject
    private WebTarget target;

    @Test
    void testHelloWorld() {
        // JsonObject jsonObject = target
        //         .path("greet")
        //         .request()
        //         .get(JsonObject.class);
        // Assertions.assertEquals("Hello World!", jsonObject.getString("message"),
        //         "default message");

        try (Response r = target
                .path("metrics")
                .request()
                .get()) {
            Assertions.assertEquals(200, r.getStatus(), "GET metrics status code");
        }

        try (Response r = target
                .path("health")
                .request()
                .get()) {
            Assertions.assertEquals(200, r.getStatus(), "GET health status code");
        }
    }
}
