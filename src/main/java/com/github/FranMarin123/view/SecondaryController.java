package com.github.FranMarin123.view;

import java.io.IOException;

import com.github.FranMarin123.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}