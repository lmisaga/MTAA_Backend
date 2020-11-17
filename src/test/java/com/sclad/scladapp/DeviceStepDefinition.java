package com.sclad.scladapp;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.DeviceType;
import com.sclad.scladapp.entity.User;
import com.sclad.scladapp.model.DeviceModel;
import com.sclad.scladapp.model.UserModel;
import com.sclad.scladapp.repository.AuthorityRepository;
import com.sclad.scladapp.repository.UserRepository;
import com.sclad.scladapp.service.DeviceService;
import com.sclad.scladapp.service.UserService;

public class DeviceStepDefinition extends SpringIntegrationTest {

	private final DeviceService deviceService;
	private final UserService userService;
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;

	private User loggedUser = null;
	private List<Device> devicesByCategory;
	private DeviceModel deviceCreateModel = new DeviceModel();
	private Device createdDevice = null;
	private String exceptionMessage = null;

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	public DeviceStepDefinition(DeviceService deviceService, UserService userService, UserRepository userRepository,
								AuthorityRepository authorityRepository) {
		this.deviceService = deviceService;
		this.userService = userService;
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}

	@Before
	public void beforeEach() {
		this.devicesByCategory = null;
		this.deviceCreateModel = new DeviceModel();
		this.createdDevice = null;
	}

	@Given("user {string} with password {string} is registered")
	public void registerUser(String username, String password) {
		if (!userRepository.findByUsername(username).isPresent()) {
			UserModel userModel = new UserModel();
			userModel.setUsername(username);
			userModel.setPassword(password);
			userModel.setPasswordConfirm(userModel.getPassword());
			userModel.setEmail(username + "@" + "sclad.com");
			userService.register(userModel);
		}
		this.loggedUser = userService.getUserByUsername(username);
		logger.info("User " + username + " is registered.");
	}

	@When("user wants to search devices by category {string}")
	public void listDevicesByCategory(String category) {
		devicesByCategory = deviceService.listAllDevicesByType(category.toUpperCase());
		logger.info("Device list of size " + devicesByCategory.size() + " returned");
	}

	@And("user {string} is logged in")
	public void userExampleUserIsLoggedIn(String username) {
		String url = baseUrl + "user/getCurrentlyLoggedUser";
		String password = username;
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

		ResponseEntity<String> responseEntity = testRestTemplate.withBasicAuth(username, password)
																.exchange(
																		url,
																		HttpMethod.GET,
																		requestEntity,
																		String.class
																);

		assert responseEntity.getStatusCode() == HttpStatus.OK &&
			   responseEntity.getBody() != null && responseEntity.getBody().contains(username);
		logger.info("User " + username + " is logged in.");
	}

	@Then("list of devices matching provided category {string} is returned")
	public void listOfDevicesMatchingProvidedCategoryNameIsReturned(String category) {
		assert devicesByCategory.stream().allMatch(x -> x.getDeviceType() == null ||
														x.getDeviceType().equals(DeviceType.valueOf(category.toUpperCase())));
		logger.info("All devices have the following category: " + category);
	}

	@When("user wants to create device with device name {string}")
	public void userWantsToCreateDeviceWithDeviceNameSomeDevice(String name) {
		this.deviceCreateModel.setProductName(name);
	}

	@And("user has permission to perform device creation")
	public void userHasPermissionToPerformDeviceCreation() {
		assert authorityRepository.findByUsername(loggedUser.getUsername()).getAuthority().equals("ROLE_ADMIN");
		logger.info("User has following permission: ROLE_ADMIN");
	}

	@And("device product number is {string}")
	public void deviceSerialNumberIsSome_device_serial_no(String productCode) {
		this.deviceCreateModel.setProductCode(productCode);
	}

	@And("device initial quantity is {int}")
	public void deviceInitialQuantityIs(int quantity) {
		this.deviceCreateModel.setQuantity(quantity);
	}

	@And("device quantity threshold is {int}")
	public void deviceQuantityThresholdIs(int quantityThreshold) {
		this.deviceCreateModel.setQuantityThreshold(quantityThreshold);
	}

	@Then("device is created in the database")
	public void thisDeviceIsCreatedInTheDatabase() {
		createdDevice = deviceService.create(deviceCreateModel);
		assert createdDevice != null;
		logger.info("Device with product name "+ createdDevice.getProductName() + " created in the database");
	}

	@And("device object with all attributes is returned")
	public void deviceObjectWithAllAttributesIsReturned() {
		assert createdDevice.getId() != null && createdDevice.getProductName().equals(deviceCreateModel.getProductName())
			   &&  createdDevice.getProductCode().equals(deviceCreateModel.getProductCode());
	}

	@And("returned device object automatically has category {string} assigned")
	public void returnedDeviceObjectAutomaticallyHasCategoryAssigned(String category) {
		assert createdDevice.getDeviceType().equals(DeviceType.valueOf(category.toUpperCase()));
		logger.info("Created device has correct device type assigned: " + createdDevice.getDeviceType().name());
	}

	@Then("device should not be created")
	public void deviceShouldNotBeCreated() {
		try {
			deviceService.create(deviceCreateModel);
		} catch (ConstraintViolationException exception) {
			exceptionMessage = exception.getMessage();
			logger.info("Device create failed (expected) on blank product code");
		}
		assert exceptionMessage != null;
	}

	@And("error message saying product number is invalid should be displayed")
	public void errorMessageSayingProductNumberIsInvalidShouldBeDisplayed() {
		System.out.println(exceptionMessage);
		assert exceptionMessage != null && exceptionMessage.toLowerCase().contains("product code");
		logger.info("Exception message displayed: " + exceptionMessage);
	}
}
