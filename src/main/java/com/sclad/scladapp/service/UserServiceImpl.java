package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.User;
import com.sclad.scladapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long register(User user) {
        if (!user.getPassword().equals(user.getPasswordConfirm()) || user.getUsername() == null) {
            throw new UsernameNotFoundException("Error in password confirm");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username not found.");
        }
    }

    @Override
    public void deleteUser(User user) {

    }
}
