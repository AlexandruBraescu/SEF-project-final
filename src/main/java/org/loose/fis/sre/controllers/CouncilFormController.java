package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.services.ConcilService;
import org.loose.fis.sre.services.UserNameTransporterService;
import java.io.IOException;

public class CouncilFormController {
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private javafx.scene.control.TextField address;
    @FXML
    private javafx.scene.control.TextField phone;
    @FXML
    private javafx.scene.control.Button confirmButton;
    @FXML
    private Text errorMessage;

    @FXML
    public void handleConfirmAction() {
        if (UserNameTransporterService.isUsernameAvailable())
            username = UserNameTransporterService.getUsername();
        else
            errorMessage.setText("ERROR, no username available");

        try {
            ConcilService.addConsumer(username.getText(), firstName.getText(), lastName.getText(),
                    address.getText(), phone.getText());

            Main m = new Main();
            m.changeScene("login.fxml");
        } catch (EmptyFieldsException ex) {
            errorMessage.setText(ex.getMessage());
        } catch (IOException ex) {
            errorMessage.setText("error");
        }
    }
}
