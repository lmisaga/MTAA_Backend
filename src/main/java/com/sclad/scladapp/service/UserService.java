package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.User;

public interface UserService {

    Long register(User user);

    User getUserByUsername(String username);

    void deleteUser(User user);



}
