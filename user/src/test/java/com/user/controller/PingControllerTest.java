package com.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PingControllerTest {

    private PingController pingController;

    @BeforeEach
    public void setUp() {
        pingController = new PingController();
    }

    @Test
    public void testPing() {
        ResponseEntity<String> responseEntity = pingController.ping();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("pong", responseEntity.getBody());
    }
}
