package com.udacity.vehicles.service;

import com.udacity.vehicles.client.prices.Price;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CarServiceTest.class)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class CarServiceTest {
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    public static MockWebServer mockBackEnd;

    private WebClient testWebClient;

    @BeforeAll
    static void setUpWebServer() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    public void setUpWebClient() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        testWebClient = WebClient.create(baseUrl);
    }


    @Test
    public void justTest() {
        Assertions.assertEquals(1, 1);
    }


    @Test
    public void testGetPrice() {
        /*
        Mono<Price> priceResponse = carService.getPrice(testWebClient);

        Assertions.assertNotNull(priceResponse);

        Price price = priceResponse.block();
        System.out.println(price.toString());
         */
    }
}
