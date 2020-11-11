package com.sclad.scladapp.model.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sclad.scladapp.repository.UserRepository;

public class UsernameUniqueValidator implements ConstraintValidator<Unique, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(Unique unique) {
		unique.message();
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return userRepository == null || !userRepository.findByUsername(username).isPresent();
	}
}
