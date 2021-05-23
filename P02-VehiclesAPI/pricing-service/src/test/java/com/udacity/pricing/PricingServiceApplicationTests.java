package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.domain.price.PriceRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {
	private static final Long TEST_PRICE_ID=Long.valueOf(98);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PriceRepository repository;

	@Test
	public void getPrice() {
		repository.save(new Price("GBP", BigDecimal.valueOf(12345), TEST_PRICE_ID));
		Price testPrice = repository.findById(TEST_PRICE_ID).get();

		ResponseEntity<Price> response =
				this.restTemplate.getForEntity("http://localhost:" + port + "/prices/" + TEST_PRICE_ID, Price.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getCurrency(), testPrice.getCurrency());
		assertEquals(response.getBody().getPrice(), testPrice.getPrice());

		repository.deleteById(TEST_PRICE_ID);
	}

	@Test
	public void contextLoads() {
	}

}
