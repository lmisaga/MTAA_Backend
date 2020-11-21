package com.sclad.scladapp;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(classes = {SpringIntegrationTest.class, ScladappApplication.class})
public class SpringIntegrationTest {

	protected final TestRestTemplate testRestTemplate = new TestRestTemplate();

	protected String baseUrl = "http://localhost:8080/api/";

	protected final String defaultAdminUsername = "exampleAdmin";

}
