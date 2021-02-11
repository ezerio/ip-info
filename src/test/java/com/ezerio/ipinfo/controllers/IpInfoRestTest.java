package com.ezerio.ipinfo.controllers;

import com.ezerio.ipinfo.dtos.IpDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:test.properties")
@ActiveProfiles("dev")
public class IpInfoRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testOkRequest() {
        IpDto validIp = new IpDto();
        validIp.setIp("1.1.1.1");
        webTestClient.post()
                .uri(IpInfoRest.BASE_PATH + IpInfoRest.TRACE)
                .bodyValue(validIp)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testBadRequest() {
        IpDto invalidIp = new IpDto();
        invalidIp.setIp("999.999.999.999");
        webTestClient.post()
                .uri(IpInfoRest.BASE_PATH + IpInfoRest.TRACE)
                .bodyValue(invalidIp)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testNotFound() {
        IpDto invalidIp = new IpDto();
        invalidIp.setIp("FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF:FFFF");
        webTestClient.post()
                .uri(IpInfoRest.BASE_PATH + IpInfoRest.TRACE)
                .bodyValue(invalidIp)
                .exchange()
                .expectStatus().isNotFound();
    }

}
