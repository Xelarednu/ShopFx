package com.shop.shopfx.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFormLoader extends AbstractFormLoader {
    public LoginFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load(){
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/user/loginForm.fxml");
        Parent root;

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        getPrimaryStage().setResizable(false);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Login");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();

    }
}