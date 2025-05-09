package com.shop.shopfx.loaders;

import com.shop.shopfx.controller.EditGpuFormController;
import com.shop.shopfx.model.entity.GraphicsCard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EditGpuFormLoader extends AbstractFormLoader {

    public EditGpuFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load() {
        throw  new UnsupportedOperationException("Not supported yet.");
    }

    public void load(GraphicsCard selectedGpu) {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/gpu/editGpuForm.fxml");
        try {
            Parent editGpuFormRoot = fxmlLoader.load();
            EditGpuFormController editGpuFormController = fxmlLoader.getController();
            editGpuFormController.setEditGpu(selectedGpu);
            Scene scene = new Scene(editGpuFormRoot);
            getPrimaryStage().setTitle("Graphics card shop - Edit GPU");
            getPrimaryStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}