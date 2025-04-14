package com.shop.shopfx.service;

import com.shop.shopfx.model.entity.User;
import com.shop.shopfx.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository UserRepository;

    public AuthService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public boolean authenticate(String username, String password) {
        Optional<User> optionalAppUser = UserRepository.findByUsername(username);
        if(optionalAppUser.isPresent()) {
            User loginUser = optionalAppUser.get();
            if(loginUser.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}