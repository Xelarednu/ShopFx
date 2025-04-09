package com.shop.shopfx.interfaces;

import com.shop.shopfx.model.entity.User;
import org.hibernate.Session;

import java.util.Optional;

public interface UserService extends AppService<User> {
    void initSuperUser();
    boolean authentication(String username, String password);
    Optional<Session> getSession();
}