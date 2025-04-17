package com.shop.shopfx.controller;

import com.shop.shopfx.interfaces.UserService;
import com.shop.shopfx.loaders.MainFormLoader;
import com.shop.shopfx.model.entity.User;
import com.shop.shopfx.security.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class AdminPanelFormController implements Initializable {
    private MainFormLoader mainFormLoader;
    private UserService userService;
    private User updateUser;

    @FXML
    private TextField tfUser;

    @FXML
    private ComboBox<Role> cbRoles;

    @FXML
    private TableColumn<User,String> tcId;

    @FXML
    private TableColumn<User,String> tcFirstname;

    @FXML
    private TableColumn<User,String> tcLastname;

    @FXML
    private TableColumn<User,String> tcUsername;

    @FXML
    private TableColumn<User,String> tcRoles;

    @FXML
    private TableView<User> tvUsers;

    public AdminPanelFormController(MainFormLoader mainFormLoader, UserService userService) {
        this.mainFormLoader = mainFormLoader;
        this.userService = userService;
    }

    private void initFieldsForEdit(User selectedUser) {
        this.updateUser = selectedUser;
        tfUser.setText(selectedUser.getUsername() + " " + selectedUser.getRoles().stream().map(role -> role.toString()).collect(Collectors.joining(", ")));
        cbRoles.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Role role, boolean empty) {
                super.updateItem(role, empty);
                setText(empty || role == null ? null : role.toString());
            }
        });
        cbRoles.getItems().clear();
        cbRoles.getItems().addAll(Role.values());
    }

    @FXML private void btAddClick(){
        if(cbRoles.getSelectionModel().isEmpty()){return;}
        userService.addRole(updateUser, cbRoles.getSelectionModel().getSelectedItem());
        tvUsers.refresh();
        clearFieldsAndSelected();
    }

    @FXML private void btRemoveClick(){
        if(cbRoles.getSelectionModel().isEmpty()){return;}
        userService.removeRole(updateUser, cbRoles.getSelectionModel().getSelectedItem());
        tvUsers.refresh();
        clearFieldsAndSelected();
    }

    private void clearFieldsAndSelected() {
        tfUser.clear();
        cbRoles.getSelectionModel().clearSelection();
        tvUsers.getSelectionModel().clearSelection();
    }

    @FXML private void showMainForm(){
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvUsers.setItems(userService.getObservableList());

        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tcFirstname.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
        tcLastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        tcUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        tcRoles.setCellValueFactory(cellData -> cellData.getValue().rolesProperty());

        tvUsers.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !tvUsers.getSelectionModel().isEmpty()) {
                User selectedUser = tvUsers.getSelectionModel().getSelectedItem();
                initFieldsForEdit(selectedUser);
            }
        });

    }
}