package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Solution;
import org.loose.fis.sre.services.ConcilService;
import org.loose.fis.sre.services.OrderIdTransporterService;
import org.loose.fis.sre.services.OrderService;

import java.io.IOException;

public class HistoryDetailsController {
    @FXML
    private Text productName;
    @FXML
    private Text quantity;
    @FXML
    private Text deliveryMethod;
    @FXML
    private Text totalPrice;
    @FXML
    private Text consumerName;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;
    Solution solution;

    @FXML
    public void initialize() {
        solution = OrderService.getOrderById(OrderIdTransporterService.getId());
        productName.setText(solution.getProduct().getName());
        quantity.setText(String.valueOf(solution.getQuantity()));
        deliveryMethod.setText(solution.getDeliveryMethod());
        totalPrice.setText(String.valueOf(solution.getTotalprice()));
        consumerName.setText(ConcilService.getConsumerByOrderId(solution.getId()).getFirstName() + " " + ConcilService.getConsumerByOrderId(solution.getId()).getLastName());
    }

    @FXML
    public void handleBackAction() {
        try {
            Main m = new Main();
            m.changeScene("studenthistoryproblems.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}

