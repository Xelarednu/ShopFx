package com.shop.shopfx;

import com.shop.shopfx.tools.FormLoader;
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
        ShopFxApplication.primaryStage = primaryStage;

        FormLoader formLoader = SpringApplication.run(ShopFxApplication.class).getBean(FormLoader.class);
        formLoader.loadLoginForm();
    }
}
