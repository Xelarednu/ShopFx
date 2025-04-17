package com.shop.shopfx.controller;

import com.shop.shopfx.interfaces.GpuService;
import com.shop.shopfx.loaders.MainFormLoader;
import com.shop.shopfx.model.entity.GraphicsCard;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditGpuFormController implements Initializable {
    private MainFormLoader mainFormLoader;
    private GpuService gpuService;
    private GraphicsCard editGpu;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfReleaseYear;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfCount;


    public EditGpuFormController(MainFormLoader mainFormLoader, GpuService gpuService) {
        this.mainFormLoader = mainFormLoader;
        this.gpuService = gpuService;
    }

    @FXML
    private void goEdit() {
        editGpu.setName(tfName.getText());
        editGpu.setReleaseYear(Integer.parseInt(tfReleaseYear.getText()));
        editGpu.setQuantity(Integer.parseInt(tfQuantity.getText()));
        editGpu.setQuantity(Integer.parseInt(tfCount.getText()));

        gpuService.add(editGpu);
        mainFormLoader.load();
    }

    @FXML private void goToMainForm() {
        mainFormLoader.load();
    }

    public void setEditGpu(GraphicsCard gpu) {
        this.editGpu = editGpu;
        tfId.setText(editGpu.getId().toString());
        tfName.setText(editGpu.getName());
        tfReleaseYear.setText(((Integer) editGpu.getReleaseYear()).toString());
        tfQuantity.setText(((Integer) editGpu.getQuantity()).toString());
        tfCount.setText(((Integer) editGpu.getQuantity()).toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfCount.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                this.goEdit();
            }
        });
    }
}