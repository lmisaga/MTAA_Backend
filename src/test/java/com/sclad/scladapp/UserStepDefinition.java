package com.sclad.scladapp;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ValidationException;

import com.sclad.scladapp.model.UserModel;
import com.sclad.scladapp.service.UserService;

public class UserStepDefinition extends SpringIntegrationTest {

	private final UserService userService;

	@Autowired
	public UserStepDefinition(UserService userService) {
		this.userService = userService;
	}

	private UserModel userRegisterModel = new UserModel();

	@Value("common.organization.title")
	private String organizationTitle;

	@BeforeEach
	public void beforeEach() {
		userRegisterModel = new UserModel();
	}

	@When("User is providing email with value {string}")
	public void userRegistersWithWrongEmailAddress(String email) {
		userRegisterModel.setEmail(email);
	}

	@And("company name is {string}")
	public boolean companyNameIs(String companyName) {
		if (companyName.equals(organizationTitle)) {
			organizationTitle = companyName;
			return true;
		} else {
			return false;
		}
	}

	@Then("registration process should fail and error code should be returned")
	public boolean registrationProcessFailureOnBadEmail() {
		return userService.register(userRegisterModel) > 0L;
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
	public boolean registrationProcessShouldSucceedAndUserIDShouldBeReturned() {
		return userService.register(userRegisterModel) > 0L;
	}

	@After("@correctRegistrationScenario")
	public void afterCreatingUser() {
		userService.deleteUser(userService.getUserByUsername(userRegisterModel.getUsername()));
	}

	@Then("registration process should fail and error code saying wrong email domain has been provided")
	public boolean registrationProcessShouldFailAndErrorCodeSayingWrongEmailDomainHasBeenProvided() {
		try {
			userService.register(userRegisterModel);
			return false;
		} catch (ValidationException exception) {
			return true;
		}
	}
}
