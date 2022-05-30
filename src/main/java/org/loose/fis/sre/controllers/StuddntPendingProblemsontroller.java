package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Solution;
import org.loose.fis.sre.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class StuddntPendingProblemsontroller {
    @FXML
    private ListView<Solution> pendingOrders;
    @FXML
    private Text errorMessage;
    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        String username = UserNameTransporterService.getUsername().getText();
        pendingOrders.getItems().clear();
        ArrayList<Solution> solutions = StudentService.getFarmerByUsername(username).getPendingOrders();

        if (!solutions.isEmpty()) {
            pendingOrders.getItems().addAll(solutions);

            pendingOrders.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Solution>() {
                @Override
                public void changed(ObservableValue<? extends Solution> observable, Solution oldValue, Solution newValue) {
                    Solution currentSolution = pendingOrders.getSelectionModel().getSelectedItem();
                    OrderIdTransporterService.setId(currentSolution.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("studentsolutiondetails.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }

    @FXML
    public void handleBackAction() {
        try {
            Main m = new Main();
            m.changeScene("studentsolutions.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
