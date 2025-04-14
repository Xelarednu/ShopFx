package com.shop.shopfx.security;

import com.shop.shopfx.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private User currentUser;

    public void login(User User) {
        this.currentUser = User;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean hasRole(Role role) {
        if (currentUser.getRoles().contains(role.toString())) {
            return true;
        } else {
            return false;
        }
    }
}