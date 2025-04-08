package com.shop.shopfx.tools;

import com.shop.shopfx.ShopFxApplication;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public FormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return ShopFxApplication.primaryStage;
    }

    private void handle(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to close the application?");
        alert.setContentText("Click 'OK' to close or 'Cancel' to continue");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        } else {
            event.consume();
        }
    }

    public void loadMainForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/main/mainForm.fxml");
        Parent root;

        try {
            root = fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException();
        }

        Scene scene = new Scene(root);

        getPrimaryStage().setResizable(false);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Main");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }

}
