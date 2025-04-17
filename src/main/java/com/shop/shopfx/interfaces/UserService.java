package com.shop.shopfx.interfaces;

import com.shop.shopfx.model.entity.User;
import com.shop.shopfx.security.Role;

public interface UserService extends AppService<User> {
    void initSuperUser();
    void addRole(User appdateUser, Role role);
    void removeRole(User appdateUser,Role role);
}