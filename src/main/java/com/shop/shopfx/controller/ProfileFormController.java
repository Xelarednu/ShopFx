package com.shop.shopfx.controller;

import com.shop.shopfx.interfaces.UserService;
import com.shop.shopfx.loaders.MainFormLoader;
import com.shop.shopfx.model.entity.User;
import com.shop.shopfx.security.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class ProfileFormController implements Initializable {
    private final MainFormLoader mainFormLoader;
    private final UserService userService;
    private final SessionManager sessionManager;

    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField pfPassword;

    public ProfileFormController(MainFormLoader mainFormLoader, UserService userService, SessionManager sessionManager) {
        this.mainFormLoader = mainFormLoader;
        this.userService = userService;
        this.sessionManager = sessionManager;
    }

    @FXML
    private void updateProfile() {
        if (sessionManager.isLoggedIn()) {
            User user = sessionManager.getCurrentUser();
            user.setFirstName(tfFirstName.getText());
            user.setLastName(tfLastName.getText());
            user.setUsername(tfUserName.getText());

            if (!pfPassword.getText().isEmpty()) {
                user.setPassword(pfPassword.getText());
            }

            Optional<User> appUserOptional = userService.add(user);
            if (appUserOptional.isPresent()) {
                sessionManager.login(appUserOptional.get());
                mainFormLoader.load("User profile has been updated");
            } else {
                mainFormLoader.load("Failed to update user profile");
            }
        }
    }

    @FXML
    private void showMainForm() {
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = sessionManager.getCurrentUser();
        if (user != null && user.getUsername() != "admin") {
            tfFirstName.setText(user.getFirstName());
            tfLastName.setText(user.getLastName());
            tfUserName.setText(user.getUsername());
            pfPassword.setText("");
        }
    }
}