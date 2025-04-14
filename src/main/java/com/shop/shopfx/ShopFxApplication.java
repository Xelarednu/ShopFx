package com.shop.shopfx;

import com.shop.shopfx.loaders.LoginFormLoader;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.stage.Stage;


@SpringBootApplication
public class ShopFxApplication extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginFormLoader loginFormLoader = SpringApplication.run(ShopFxApplication.class).getBean(LoginFormLoader.class);
        loginFormLoader.setPrimaryStage(primaryStage);
        loginFormLoader.load();
    }
}