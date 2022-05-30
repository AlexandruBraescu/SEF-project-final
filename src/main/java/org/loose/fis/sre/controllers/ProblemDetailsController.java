package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.dizitart.no2.NitriteId;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.model.Problem;
import org.loose.fis.sre.services.ProblemTransportService;
import org.loose.fis.sre.services.ProductService;

import java.io.IOException;

public class ProblemDetailsController {
    private NitriteId productId;
    @FXML
    private TextField name;
    @FXML
    private TextArea description;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private Button editProductbutton;
    @FXML
    private Button removeProductButton;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;

    @FXML
    public void initialize() {
        productId = ProblemTransportService.getProductId();
        Problem p;
        try {
            p = ProductService.getProductById(productId);
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
            return;
        }

        name.setText(p.getName());
        description.setText(p.getDescription());
        quantity.setText(String.valueOf(p.getQuantity()));
        price.setText(String.valueOf(p.getPricePerUnit()));
    }

    @FXML
    public void handleEditAction() {
        try {
            ProductService.updateProductById(productId, name.getText(), description.getText(), quantity.getText(), price.getText());

            Main m = new Main();
            m.changeScene("studentproblemlist.fxml");
        } catch (NotANumberException | EmptyFieldsException | IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackAction() {
        Main m = new Main();
        try {
            m.changeScene("studentproblemlist.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleRemoveAction() {
        ProductService.removeProduct(productId);

        Main m = new Main();
        try {
            m.changeScene("studentproblemlist.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
