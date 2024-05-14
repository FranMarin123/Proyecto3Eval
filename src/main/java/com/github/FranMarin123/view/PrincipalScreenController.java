package com.github.FranMarin123.view;

import java.io.IOException;

import com.github.FranMarin123.App;
import javafx.fxml.FXML;

public class PrincipalScreenController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
