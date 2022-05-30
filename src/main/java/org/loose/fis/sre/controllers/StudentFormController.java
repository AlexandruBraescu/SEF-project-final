package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.services.StudentService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;

public class StudentFormController {
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextArea description;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private Button statusButton;
    @FXML
    private Text availabilityText;
    @FXML
    private Button confirmButton;
    @FXML
    private Text errorMessage;

    private boolean status = false;
    @FXML
    public void initialize() { availabilityText.setText("Busy"); }

    @FXML
    public void toggleStatus() {
        status = !status;

        if (status)
            availabilityText.setText("Available");
        else
            availabilityText.setText("Busy");
    }

    @FXML
    public void handleConfirmAction() {
        if (UserNameTransporterService.isUsernameAvailable())
            username = UserNameTransporterService.getUsername();
        else
            errorMessage.setText("ERROR, no username available");

        try {
            StudentService.addStudent(username.getText(), firstName.getText(), lastName.getText(), description.getText(),
                    address.getText(), phone.getText(), status);

            Main m = new Main();
            m.changeScene("login.fxml");
        } catch (EmptyFieldsException ex) {
            errorMessage.setText(ex.getMessage());
        } catch (IOException ex) {
            errorMessage.setText("error");
        }
    }
}
