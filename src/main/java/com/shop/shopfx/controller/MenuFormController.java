package com.shop.shopfx.controller;

import com.shop.shopfx.loaders.BoughtGpuListFormLoader;
import com.shop.shopfx.loaders.LoginFormLoader;
import com.shop.shopfx.loaders.NewGpuFormLoader;
import com.shop.shopfx.security.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {
    private final NewGpuFormLoader newGpuFormLoader;
    private final LoginFormLoader loginFormLoader;
    private final BoughtGpuListFormLoader boughtGpuListFormLoader;
    private final ProfileFormLoader profileFormLoader;
    private final AdminPanelFormLoader adminPanelFormLoader;

    private final SessionManager sessionManager;
    private Parent rootMenuForm;

    @FXML private Menu mGpu;
    @FXML private Menu mAdmin;
    @FXML private Menu mUsers;
    @FXML private MenuItem miLogin;
    @FXML private MenuItem miProfile;
    @FXML private MenuItem miLogout;

    public MenuFormController(SessionManager sessionManager, NewGpuFormLoader newGpuFormLoader, LoginFormLoader loginFormLoader, BoughtGpuListFormLoader boughtGpuListFormLoader, ProfileFormLoader profileFormLoader, AdminPanelFormLoader adminPanelFormLoader) {
        this.sessionManager = sessionManager;
        this.newGpuFormLoader = newGpuFormLoader;
        this.loginFormLoader = loginFormLoader;
        this.boughtGpuListFormLoader = boughtGpuListFormLoader;
        this.profileFormLoader = profileFormLoader;
        this.adminPanelFormLoader = adminPanelFormLoader;
    }

    @FXML private void showBookForm() {
        newGpuFormLoader.load();
    }

    @FXML private void showLoginForm(){
        loginFormLoader.load();
    }

    @FXML private void logout(){
        this.sessionManager.logout();
        loginFormLoader.load();
    }

    // Not sure about this
    @FXML private void showBoughtGpuForm(){
        boughtGpuListFormLoader.load();
    }
    // Not sure about this

    @FXML private void showProfileForm(){
        profileFormLoader.load();
    }

    @FXML private void showAdminPanelForm(){
        adminPanelFormLoader.load();
    }

    private void initMenuVisible(){
        if(sessionManager.isLoggedIn()){
            if(sessionManager.getCurrentUser().getRoles().contains(Role.ADMINISTRATOR.toString())){
                mBooks.setVisible(true);
                mAdmin.setVisible(true);
                mUsers.setVisible(true);
                miEnter.setVisible(false);
                miProfile.setVisible(true);
                miLogout.setVisible(true);
            }else if(sessionManager.getCurrentUser().getRoles().contains(Role.MANAGER.toString())){
                mBooks.setVisible(true);
                mAdmin.setVisible(false);
                mUsers.setVisible(true);
                miEnter.setVisible(false);
                miProfile.setVisible(true);
                miLogout.setVisible(true);
            }else if(sessionManager.getCurrentUser().getRoles().contains(Role.USER.toString())){
                mBooks.setVisible(false);
                mAdmin.setVisible(false);
                mUsers.setVisible(true);
                miEnter.setVisible(false);
                miProfile.setVisible(true);
                miLogout.setVisible(true);
            }
        }

    }

    public void setRootMenuForm(Parent root) {
        this.rootMenuForm = root;
    }

    public Node getRootMenuForm() {
        return rootMenuForm;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }
}