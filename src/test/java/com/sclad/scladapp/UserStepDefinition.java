package com.sclad.scladapp;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.*;

import javax.validation.ValidationException;

import com.sclad.scladapp.entity.User;
import com.sclad.scladapp.model.UserModel;
import com.sclad.scladapp.service.UserService;

public class UserStepDefinition extends SpringIntegrationTest {

	private final Logger logger = LoggerFactory.getLogger(UserStepDefinition.class);

	private final UserService userService;

	@Autowired
	public UserStepDefinition(UserService userService) {
		this.userService = userService;
	}

	private UserModel userRegisterModel = new UserModel();

	@Value("${common.organization.title}")
	private String organizationTitle;

	@Before
	public void beforeEach() {
		userRegisterModel = new UserModel();
		User user;
		try {
			user = userService.getUserByUsername("exampleUser");
			userService.deleteUser(user);
		} catch (UsernameNotFoundException ignored) {}
	}

	@When("User is providing email with value {string}")
	public void userRegistersWithWrongEmailAddress(String email) {
		userRegisterModel.setEmail(email);
	}

	@Given("company name is {string}")
	public boolean companyNameIs(String companyName) {
		logger.info("Current company name: " + organizationTitle + ", provided company name: " + companyName);
		if (companyName.equals(organizationTitle)) {
			organizationTitle = companyName;
			return true;
		} else {
			return false;
		}
	}

	@And("user provides unique username {string}")
	public void userProvidesUsername(String userName) {
		userRegisterModel.setUsername(userName);
	}

	@And("user provides password {string}")
	public void userProvidesPassword(String password) {
		userRegisterModel.setPassword(password);
	}

	@And("user provides password check {string}")
	public void userProvidesPasswordCheck(String passwordConfirm) {
		userRegisterModel.setPasswordConfirm(passwordConfirm);
	}

	@Then("registration process should succeed and user ID should be returned")
	public void registrationProcessShouldSucceedAndUserIDShouldBeReturned() {
		assert(userService.register(userRegisterModel) > 0L);
		logger.info("User successfully registered.");
	}

	@Then("registration process should fail and error code should be returned")
	public boolean registrationProcessFailureOnBadEmail() {
		return userService.register(userRegisterModel) > 0L;
	}

	@After("@Tag")
	public void afterCreatingUser() {
		try {
			userService.deleteUser(userService.getUserByUsername(userRegisterModel.getUsername()));
		} catch (Exception ignored) {
		}
	}

	@Then("registration process should fail and error code saying wrong email domain has been provided")
	public boolean registrationProcessShouldFailAndErrorCodeSayingWrongEmailDomainHasBeenProvided() {
		try {
			userService.register(userRegisterModel);
			return false;
		} catch (ValidationException exception) {
			logger.info("Registration failed with following exception: " + exception.getMessage());
			return true;
		}
	}

	@Then("registration process should fail and error code saying insufficient password has been provided")
	public void registrationProcessShouldFailAndErrorCodeSayingInsufficientPasswordHasBeenProvided() {
		boolean retval = true;
		try {
			userService.register(userRegisterModel);
			retval = false;
		} catch (ValidationException exception) {
			logger.info("Registration failed with following exception: " + exception.getMessage());
		}
		assertTrue(retval);
	}
}
