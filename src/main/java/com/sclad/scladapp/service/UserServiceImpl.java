package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.Authority;
import com.sclad.scladapp.entity.User;
import com.sclad.scladapp.exceptions.UserNotFoundException;
import com.sclad.scladapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Authority role = new Authority();
        role.setUsername(user.getUsername());
        role.setAuthority(user.getUsername().contains("admin") ? "ROLE_ADMIN" : "ROLE_USER");
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
    public ResponseEntity<String> deleteUser(User user) {
        if (user.getId() != null && userRepository.findById(user.getId()).isPresent()) {
            userRepository.delete(user);
            return new ResponseEntity<>("User removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getId()).orElse(null);
        if (user != null) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            return userRepository.save(updatedUser);
        } else {
            try {
                throw new UserNotFoundException("Could not update user - no user was found matching the provided model.");
            } catch (UserNotFoundException e) {
                return null;
            }
        }
    }
}
