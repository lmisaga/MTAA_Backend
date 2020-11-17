package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.User;
import com.sclad.scladapp.model.UserModel;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface UserService {

    Long register(@Valid UserModel user);

    User getUserByUsername(String username);

    ResponseEntity<String> deleteUser(User user);

    User updateUser(User updatedUser);
}
