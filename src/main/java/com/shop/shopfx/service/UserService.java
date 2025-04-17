package com.shop.shopfx.service;

import com.shop.shopfx.model.entity.User;
import com.shop.shopfx.model.repository.UserRepository;
import com.shop.shopfx.security.Role;
import com.shop.shopfx.security.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements com.shop.shopfx.interfaces.UserService {
    private final SessionManager sessionManager;
    private final UserRepository userRepository;

    public UserService(SessionManager sessionManager, UserRepository userRepository) {
        this.sessionManager = sessionManager;
        this.userRepository = userRepository;

        initSuperUser();
    }

    @Override
    public void initSuperUser() {
        if (userRepository.count() > 0) {
            return;
        }

        User admin = new User();
        admin.setFirstName("Jane");
        admin.setLastName("Doe");
        admin.setUsername("superAdmin");
        admin.setPassword("12345");

        admin.getRoles().add(Role.ADMIN.toString());
        admin.getRoles().add(Role.MANAGER.toString());
        admin.getRoles().add(Role.USER.toString());

        userRepository.save(admin);
    }

    @Override
    public void addRole(User user, Role role) {
        if (sessionManager.getCurrentUser().getRoles().contains(Role.ADMIN.toString())) {
            user.getRoles().add(role.toString());
            saveAndLogin(user);
        }
    }

    @Override
    public void removeRole(User user, Role role) {
        if (sessionManager.getCurrentUser().getRoles().contains(Role.ADMIN.toString())) {
            if (user.getUsername().equals("admin")) {
                return;
            }

            if (user.getRoles().contains(role.toString()) && role != Role.USER) {
                user.getRoles().remove(role.toString());
                saveAndLogin(user);
            }
        }
    }

    private void saveAndLogin(User appUser){
        User updateUser = userRepository.save(appUser);
        if (appUser.getUsername().equals(sessionManager.getCurrentUser().getUsername())) {
            sessionManager.login(updateUser);
        }
    }

    @Override
    public Optional<User> add(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public List<User> getList() {
        if (sessionManager.isLoggedIn() && (sessionManager.hasRole(Role.MANAGER) || sessionManager.hasRole(Role.ADMIN))) {
            return userRepository.findAll();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public ObservableList<User> getObservableList() {
        ObservableList<User> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.getList());
        return observableList;
    }
}