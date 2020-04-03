package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Long register(User user);

    User getUserByUsername(String username);

    ResponseEntity<String> deleteUser(User user);

    User updateUser(User updatedUser);




}
