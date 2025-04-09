package com.shop.shopfx.controller;

import com.shop.shopfx.interfaces.UserService;
import com.shop.shopfx.tools.FormLoader;
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

    private FormLoader formLoader;
    private UserService userService;

    @FXML
    private Label lblInfo;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;

    public LoginController(FormLoader formLoader, UserService userService) {
        this.formLoader = formLoader;
        this.userService = userService;
    }

    @FXML
    private void login() {
        if (userService.authentication(tfUsername.getText(), pfPassword.getText())) {
            formLoader.loadMainForm();
        } else {
            lblInfo.setText("Invalid login or password");
        }
    }

    @FXML
    private void showRegistrationForm() {
        formLoader.loadRegistrationForm();
    }
}
