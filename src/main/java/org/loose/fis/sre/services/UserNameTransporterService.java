package org.loose.fis.sre.services;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserNameTransporterService {
    private static boolean usernameAvailable = false;

    @FXML
    private static TextField username;

    public static boolean isUsernameAvailable() {
        return usernameAvailable;
    }

    public static void setUsername(TextField username) {
        UserNameTransporterService.username = username;
        usernameAvailable = true;
    }

    public static TextField getUsername() {
        return username;
    }
}
