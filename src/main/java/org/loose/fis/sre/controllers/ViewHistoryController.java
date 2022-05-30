package org.loose.fis.sre.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Solution;
import org.loose.fis.sre.services.OrderIdTransporterService;
import org.loose.fis.sre.services.OrderService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;
import java.util.ArrayList;


public class ViewHistoryController {

    @FXML
    private Button backButton;
    @FXML
    private ListView<Solution> listView;
    @FXML
    private Text errorMessage;


    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("consumerProfile.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        String username = UserNameTransporterService.getUsername().getText();
        ArrayList<Solution> solutions = OrderService.getAllOrdersByUsername(username);

        listView.getItems().clear();
        if(!solutions.isEmpty()) {
            listView.getItems().addAll(solutions);
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Solution>() {
                @Override
                public void changed(ObservableValue<? extends Solution> observable, Solution oldValue, Solution newValue) {
                    Solution currentSolution = listView.getSelectionModel().getSelectedItem();
                    OrderIdTransporterService.setId(currentSolution.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("solutiondetails.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }
}



