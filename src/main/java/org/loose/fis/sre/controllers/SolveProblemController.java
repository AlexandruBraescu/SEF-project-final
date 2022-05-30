package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Council;
import org.loose.fis.sre.model.Problem;
import org.loose.fis.sre.services.*;

import java.io.IOException;

public class SolveProblemController {
    @FXML
    private Text productName;
    @FXML
    private Text description;
    @FXML
    private Text quantityAvailable;
    @FXML
    private TextField desiredAmount;
    @FXML
    private Button confirmButton;
    @FXML
    private ChoiceBox pickUp;
    @FXML
    private Button back;
    @FXML
    private  Text errorMessage;
    @FXML
    private Text price;
    @FXML
    public void initialize(){
        pickUp.getItems().addAll("pick-up", "delivery");
        try {
            Problem p = ProductService.getProductById(ProblemTransportService.getProductId());
            description.setText(p.getDescription());
            quantityAvailable.setText(String.valueOf(p.getQuantity()));
            price.setText(String.valueOf(p.getPricePerUnit()));
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("studentDetails.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleConfirmAction() {
        Problem p = null;
        try {
            p = ProductService.getProductById(ProblemTransportService.getProductId());
            String username = UserNameTransporterService.getUsername().getText();
            Council c = ConcilService.getConsumerByUsername(username);
            StudentService.addOrderToFarmer(p, c, desiredAmount.getText(), (String) pickUp.getSelectionModel().getSelectedItem());


            Main m = new Main();
            m.changeScene("studentDetails.fxml");
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }
    }

}
