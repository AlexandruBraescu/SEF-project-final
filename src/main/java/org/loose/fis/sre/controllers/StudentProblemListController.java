package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Problem;
import org.loose.fis.sre.services.StudentService;
import org.loose.fis.sre.services.ProblemTransportService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;
import java.util.*;

public class StudentProblemListController {
    @FXML
    private TextField username;
    @FXML
    private ListView<Problem> productsList;
    @FXML
    private Button addProductButton;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;
    @FXML
    public void initialize() {
        username = UserNameTransporterService.getUsername();
        productsList.getItems().clear();
        ArrayList<Problem> problems = StudentService.getAllProductsByUsername(username.getText());

        if (!problems.isEmpty()) {
            productsList.getItems().addAll(problems);

            productsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Problem>() {
                @Override
                public void changed(ObservableValue<? extends Problem> observable, Problem oldValue, Problem newValue) {
                    Problem currentProblem = productsList.getSelectionModel().getSelectedItem();
                    ProblemTransportService.setProductId(currentProblem.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("problemdetails.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }

    @FXML
    public void handleAddProductAction() {
        try {
            Main m = new Main();
            m.changeScene("addProblem.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
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
