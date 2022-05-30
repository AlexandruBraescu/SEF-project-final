package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.dizitart.no2.NitriteId;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.DeclinedOrderCanNotDeliverException;
import org.loose.fis.sre.model.Solution;
import org.loose.fis.sre.model.OrderStatusEnum;
import org.loose.fis.sre.services.OrderIdTransporterService;
import org.loose.fis.sre.services.OrderService;

import java.io.IOException;

public class SolutionDetailsController {
    private NitriteId orderId;
    private Solution solution;
    @FXML
    private Text productName;
    @FXML
    private Text quantity;
    @FXML
    private Text totalPrice;
    @FXML
    private Text deliveryMethod;
    @FXML
    private Button orderStatus;
    @FXML
    private Text errorMessage;
    @FXML
    private Button backButton;
    @FXML
    public void initialize(){
        orderId = OrderIdTransporterService.getId();
        solution = OrderService.getOrderById(orderId);
        productName.setText(solution.getProduct().getName());
        quantity.setText(String.valueOf(solution.getQuantity()));
        totalPrice.setText(String.valueOf(solution.getTotalprice()));
        deliveryMethod.setText(solution.getDeliveryMethod());
    }

    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("viewHistory.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleOrderStatus(){
        try{
            if(OrderService.getOrderById(orderId).getStatus() != OrderStatusEnum.Accepted)
                throw new DeclinedOrderCanNotDeliverException();
            OrderService.changeOrderStatus(orderId, OrderStatusEnum.Delivered);
            Main m = new Main();
            m.changeScene("viewHistory.fxml");
        } catch (DeclinedOrderCanNotDeliverException | IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }


}
