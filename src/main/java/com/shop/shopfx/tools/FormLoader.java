package com.shop.shopfx.tools;

import com.shop.shopfx.ShopFxApplication;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class FormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public FormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ShopFxApplication.primaryStage;
    }


}
