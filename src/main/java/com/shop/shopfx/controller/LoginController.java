package com.shop.shopfx.controller;

import com.shop.shopfx.loaders.MainFormLoader;
import com.shop.shopfx.service.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginController {
    @FXML
    private void initialize() {
    }

    private AuthService authService;
    private MainFormLoader mainFormLoader;
    private final RegistrationFormLoader registrationFormLoader;

    @FXML
    private Label lblInfo;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;

    public LoginController(AuthService authService, MainFormLoader mainFormLoader, RegistrationFormLoader registrationFormLoader) {
        this.authService = authService;
        this.mainFormLoader = mainFormLoader;
        this.registrationFormLoader = registrationFormLoader;
    }

    @FXML
    private void login() {
        if (authService.authenticate(tfUsername.getText(), pfPassword.getText())) {
            mainFormLoader.load();
        } else {
            lblInfo.setText("Invalid login or password");
        }
    }

    @FXML
    private void showRegistrationForm() {
        registrationFormLoader.load();
    }
}
