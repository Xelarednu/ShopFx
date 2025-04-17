package com.shop.shopfx.controller;

import com.shop.shopfx.interfaces.GpuService;
import com.shop.shopfx.loaders.MainFormLoader;
import com.shop.shopfx.model.entity.GraphicsCard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class NewGpuFormController implements Initializable {
    private final MainFormLoader mainFormLoader;
    private final GpuService gpuService;
    
    @FXML
    private Label lbInfo;
    
    @FXML
    private TextField tfName;
    
    @FXML
    private TextField tfManufacturer;
    
    @FXML
    private TextField tfReleaseYear;
    
    @FXML
    private TextField tfQuantity;

    public NewGpuFormController(MainFormLoader mainFormLoader, GpuService gpuService) {
        this.mainFormLoader = mainFormLoader;
        this.gpuService = gpuService;
    }

    @FXML
    private void create() {
        GraphicsCard graphicsCard = new GraphicsCard();
        
        if (tfName.getText().isEmpty() || tfReleaseYear.getText().isEmpty() || tfQuantity.getText().isEmpty() || tfManufacturer.getText().isEmpty()) {
            lbInfo.setText("Fill in all fields");
            return;
        }

        graphicsCard.setName(tfName.getText());
        graphicsCard.setReleaseYear(Integer.parseInt(tfReleaseYear.getText()));
        graphicsCard.setQuantity(Integer.parseInt(tfQuantity.getText()));
        gpuService.add(graphicsCard);
        mainFormLoader.load();
    }

    @FXML
    private void goToMainForm() {
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfQuantity.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                this.create();
            }
        });
    }
}