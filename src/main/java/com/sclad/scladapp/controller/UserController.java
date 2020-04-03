package com.sclad.scladapp.controller;

import com.sclad.scladapp.entity.User;
import com.sclad.scladapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Long register(@RequestBody @Valid User user) {
        return userService.register(user);
    }
}
