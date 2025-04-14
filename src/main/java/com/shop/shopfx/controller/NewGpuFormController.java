package com.shop.shopfx.controller;

import com.shop.shopfx.loaders.AbstractFormLoader;
import com.shop.shopfx.loaders.MainFormLoader;
import com.shop.shopfx.loaders.SpringFXMLLoader;
import com.shop.shopfx.model.entity.GraphicsCard;
import com.shop.shopfx.service.GpuService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.io.File;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class NewGpuFormController extends AbstractFormLoader {
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

    @FXML private void create(){
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

    @FXML private void goToMainForm(){
        mainFormLoader.load();
    }
}