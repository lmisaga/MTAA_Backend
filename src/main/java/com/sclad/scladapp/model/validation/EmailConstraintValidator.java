package com.sclad.scladapp.model.validation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<EmailConstraint, String> {

	@Value("${common.organization.title}")
	private String companyName;

	@Override
	public void initialize(EmailConstraint constraintAnnotation) {
		if (this.companyName == null)
			this.companyName = "sclad";
	}

	@Override
	public boolean isValid(String emailValue, ConstraintValidatorContext constraintValidatorContext) {
		return emailValue != null && emailValue.toLowerCase().contains(companyName.toLowerCase());
	}
}
