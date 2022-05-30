package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.model.Student;
import org.loose.fis.sre.services.StudentService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;

public class StudentEditController {
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
    @FXML
    private TextField username;
    private boolean status;
    @FXML
    public void initialize() {
        username = UserNameTransporterService.getUsername();

        Student f = StudentService.getFarmerByUsername(username.getText());
        firstName.setText(f.getFirstName());
        lastName.setText(f.getLastName());
        description.setText(f.getDescription());
        address.setText(f.getAddress());
        phone.setText(f.getPhone());
        status = f.isAvailabilityStatus();

        if (status)
            availabilityText.setText("Available");
        else
            availabilityText.setText("Busy");
    }

    @FXML
    public void handleConfirmAction() {
        try {
            StudentService.updateFarmerByUsername(username.getText(), firstName.getText(), lastName.getText(), description.getText(), address.getText(), phone.getText(), status);
            Main m = new Main();
            m.changeScene("studentprofile.fxml");
        } catch (EmptyFieldsException | IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void toggleStatus() {
        status = !status;

        if (status)
            availabilityText.setText("Available");
        else
            availabilityText.setText("Busy");
    }

    @FXML
    public void handleBackAction() {
        try {
            Main m = new Main();
            m.changeScene("studentprofile.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
