package com.sclad.scladapp.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sclad.scladapp.model.validation.EmailConstraint;

public class UserModel extends AbstractModel {

    @Email(message = "Email should be valid: <name>@<domain>.xxx")
	@EmailConstraint(message = "Email does not contain company domain name!")
    private String email;

    @NotBlank(message = "Invalid username provided!")
	@Length(min = 5, max = 50)
	private String username;

    @NotBlank(message = "Insufficient password provided!")
	@Length(min = 5, max = 63)
    private String password;

    @NotBlank(message = "Please provide password confirmation!")
    private String passwordConfirm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
