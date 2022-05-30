package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.EmptyUsernameOrPasswordException;
import org.loose.fis.sre.exceptions.UsernameAndPasswordDoNotMatchException;
import org.loose.fis.sre.services.UserNameTransporterService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LoginController {

    public LoginController() {
    }

    @FXML
    private Text wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Text registerLink;

    public void handleLogInAction() {
        try {
            UserService.logInUser(username.getText(), password.getText());
            String role = UserService.getRoleByUsername(username.getText());

            UserNameTransporterService.setUsername(username);

            Main m = new Main();
            m.changeScene("studentprofile.fxml");
            if (role.equals("Council"))
                m.changeScene("consumerProfile.fxml");
            else
                m.changeScene("studentprofile.fxml");
        } catch (EmptyUsernameOrPasswordException | UsernameAndPasswordDoNotMatchException | IOException ex) {
            wrongLogin.setText(ex.getMessage());
        }
    }

    @FXML
    public void handleRegisterAction() {
        try {
            Main m = new Main();
            m.changeScene("register.fxml");
        } catch (IOException e) {
            wrongLogin.setText("error");
        }
    }
}
