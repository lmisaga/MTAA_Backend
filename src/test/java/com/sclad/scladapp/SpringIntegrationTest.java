package com.sclad.scladapp;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@CucumberContextConfiguration
@SpringBootTest
public class SpringIntegrationTest {

	protected final TestRestTemplate testRestTemplate = new TestRestTemplate();

	protected String baseUrl = "http://localhost:8080/api/";

	protected final String defaultAdminUsername = "exampleAdmin";

}
