package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Student;
import org.loose.fis.sre.services.StudentService;
import org.loose.fis.sre.services.StudentUsernameTransportService;

import java.io.IOException;
import java.util.ArrayList;

public class ConsumerProfileController {
    @FXML
    private ChoiceBox selectChoiceBox;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Button viewHistoryButton;
    @FXML
    private ListView<Student> listView;
    @FXML
    private Text errorMessage;
    @FXML
    private Button logOutButton;

    @FXML
    public void initialize(){
        selectChoiceBox.getItems().addAll("product", "county");
    }

    @FXML
    public void handleSearchAction(){
        errorMessage.setText("");
        try{
            if(selectChoiceBox.getSelectionModel().isEmpty())
                throw new Exception("No filter selected");
            ArrayList<Student> students = new ArrayList<Student>();
            students = StudentService.filter(searchBar.getText(), (String)selectChoiceBox.getSelectionModel().getSelectedItem());
            listView.getItems().clear();
            listView.getItems().addAll(students);
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
                @Override
                public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                    Student currentStudent = listView.getSelectionModel().getSelectedItem();
                    StudentUsernameTransportService.setUsername(currentStudent.getUsername());
                    Main m = new Main();
                    try {
                        m.changeScene("studentDetails.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }


    }

    @FXML
    public void handleViewHistory(){
        try{
            Main m = new Main();
            m.changeScene("viewHistory.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogOutAction() {
        try{
            Main m = new Main();
            m.changeScene("login.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
