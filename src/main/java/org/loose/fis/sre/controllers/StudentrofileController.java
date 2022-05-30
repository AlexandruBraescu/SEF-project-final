package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;

public class StudentrofileController {
    @FXML
    private TextField username;
    @FXML
    private Text greeting;
    @FXML
    private Button editButton;
    @FXML
    private Button productsButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Text errorMessage;
    @FXML
    private Button logOutButton;

    @FXML
    public void initialize() {
        if (UserNameTransporterService.isUsernameAvailable())
            username = UserNameTransporterService.getUsername();
        else
            errorMessage.setText("ERROR, no username available");

        greeting.setText("Hello, " + username.getText() + "!\uD83E\uDDD1\u200D\uD83C\uDF3E");
    }

    @FXML
    public void handleEditAction() {
        try {
            Main m = new Main();
            m.changeScene("studentEdit.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleProductsAction() {
        try {
            Main m = new Main();
            m.changeScene("studentproblemlist.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleOrdersAction() {
        try {
            Main m = new Main();
            m.changeScene("studentsolutions.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLogOutAction() {
        try{
            Main m = new Main();
            m.changeScene("login.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
