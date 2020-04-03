package com.sclad.scladapp.controller;

import com.sclad.scladapp.entity.User;
import com.sclad.scladapp.model.UserModel;
import com.sclad.scladapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getByUsername/{username}", method = RequestMethod.GET)
    public User getByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Long register(@RequestBody @Valid UserModel user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/getCurrentlyLoggedUser", method = RequestMethod.GET)
    public Authentication getCurrentlyLoggedUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User updateUser(@RequestBody @Valid User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody @Valid User user) {
        userService.deleteUser(user);
    }
}
