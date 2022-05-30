package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Solution;
import org.loose.fis.sre.services.StudentService;
import org.loose.fis.sre.services.OrderIdTransporterService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;
import java.util.ArrayList;

public class StudentOrdersController {
    @FXML
    private ListView<Solution> ordersHistory;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;

    @FXML
    void initialize() {
        String username = UserNameTransporterService.getUsername().getText();
        ArrayList<Solution> solutions = StudentService.getFarmerByUsername(username).getOrderHistory();

        ordersHistory.getItems().clear();
        if (!solutions.isEmpty()) {
            ordersHistory.getItems().addAll(solutions);

            ordersHistory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Solution>() {
                @Override
                public void changed(ObservableValue<? extends Solution> observable, Solution oldValue, Solution newValue) {
                    Solution currentSolution = ordersHistory.getSelectionModel().getSelectedItem();
                    OrderIdTransporterService.setId(currentSolution.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("studentsolutionhistorydetails.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }

    @FXML
    public void handleBackAction(){
        try{
            Main m = new Main();
            m.changeScene("studentsolutions.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
