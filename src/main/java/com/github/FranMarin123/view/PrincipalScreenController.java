package com.github.FranMarin123.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.FranMarin123.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PrincipalScreenController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView login;

    @FXML
    private ImageView register;

    @FXML
    private ImageView exit;

    @Override
    public void onOpen(Object input) {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void enteringLoginImg(){
        login.setFitHeight(85);
        login.setFitWidth(85);
        anchorPane.setRightAnchor(login,65.0);
    }

    public void exitingLoginImg(){
        login.setFitHeight(105);
        login.setFitWidth(105);
        anchorPane.setRightAnchor(login,55.0);
    }

    public void enteringRegisterImg(){
        register.setFitHeight(85);
        register.setFitWidth(85);
        anchorPane.setRightAnchor(register,65.0);
    }

    public void exitingRegisterImg(){
        register.setFitHeight(105);
        register.setFitWidth(105);
        anchorPane.setRightAnchor(register,55.0);
    }


    public void enteringExitImg(){
        exit.setFitWidth(85);
        exit.setFitHeight(85);
        exit.setFitWidth(85);
        anchorPane.setRightAnchor(exit,65.0);
    }

    public void exitingExitImg(){
        exit.setFitHeight(105);
        exit.setFitWidth(105);
        anchorPane.setRightAnchor(exit,55.0);
    }

    public void exitClick(){
        System.exit(0);
    }

    public void loginClick(){

    }
}
