package com.github.FranMarin123.view;

import com.github.FranMarin123.model.dao.SubjectDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class createSubjectController extends Controller implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private Spinner<Integer> hours;


    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //public boolean registerSubject(){
    //}

    /*public void createButton(){
        if (name.getText()!=null && !name.getText().isEmpty() && hours.getValue()>0
                && SubjectDAO.build().findByX(name.getText(),"name")==null){
            if (){

            }
        }
    }*/
}
