package com.shop.shopfx.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BoughtGpuListFormLoader extends AbstractFormLoader {
    public BoughtGpuListFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load() {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/gpu/listBoughtGpuForm.fxml");
        Parent root;

        try {
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            getPrimaryStage().setScene(scene);
            getPrimaryStage().setTitle("List of bought gpus");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}