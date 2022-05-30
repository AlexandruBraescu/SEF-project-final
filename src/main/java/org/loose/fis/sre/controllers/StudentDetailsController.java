package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Problem;
import org.loose.fis.sre.model.Student;
import org.loose.fis.sre.services.StudentService;
import org.loose.fis.sre.services.StudentUsernameTransportService;
import org.loose.fis.sre.services.ProblemTransportService;

import java.io.IOException;
import java.util.ArrayList;


public class StudentDetailsController {
    @FXML
    private Text lastname;
    @FXML
    private Text firstname;
    @FXML
    private Text description;
    @FXML
    private Text address;
    @FXML
    private Text phoneNumber;
    @FXML
    private ListView<Problem> listView;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;
    private String username;
    @FXML
    public void initialize() {
        username = StudentUsernameTransportService.getUsername();
        Student f = StudentService.getFarmerByUsername(username);
        lastname.setText(f.getLastName());
        firstname.setText(f.getFirstName());
        description.setText(f.getDescription());
        address.setText(f.getAddress());
        phoneNumber.setText(f.getPhone());

        listView.getItems().clear();
        ArrayList<Problem> problems = StudentService.getAllProductsByUsername(username);

        if (!problems.isEmpty()) {
            listView.getItems().addAll(problems);

            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Problem>() {
                @Override
                public void changed(ObservableValue<? extends Problem> observable, Problem oldValue, Problem newValue) {
                    Problem currentProblem = listView.getSelectionModel().getSelectedItem();
                    ProblemTransportService.setProductId(currentProblem.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("solveProblem.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }
    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("consumerProfile.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

}
