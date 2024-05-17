package com.github.FranMarin123.utils;

import com.github.FranMarin123.App;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JavaFXUtils {
    public static void showAlert(String title, String textAboutAlert){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        if (errorAlert.getDialogPane().getScene().getWindow()!=null) {
            Stage alertStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(App.class.getResourceAsStream("images/logoWindow.png")));
        }
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(textAboutAlert);
        errorAlert.showAndWait();
    }
}
