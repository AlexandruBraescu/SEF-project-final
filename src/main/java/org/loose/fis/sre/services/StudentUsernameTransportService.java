package org.loose.fis.sre.services;

import javafx.fxml.FXML;

public class StudentUsernameTransportService {
    @FXML
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        StudentUsernameTransportService.username = username;
    }
}
