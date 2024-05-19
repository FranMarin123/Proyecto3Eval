package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.ActivityDAO;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.entity.Inscription;
import com.github.FranMarin123.model.enums.ActivityField;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.utils.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateActivityController extends Controller implements Initializable {

    @FXML
    private TextArea description;

    @FXML
    private TextField name;

    @FXML
    private TextField percent=new TextField();

    @FXML
    private ImageView back;

    @Override
    public void onOpen(Object input) throws IOException {
        percent.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.SELECTEDSUBJECT,null);
    }

    public void enteringBackImg() {
        back.setFitWidth(45);
        back.setFitHeight(25);
    }

    public void exitingBackImg() {
        back.setFitHeight(52);
        back.setFitWidth(32);
    }

    public void createButton() throws IOException {
        if (description!=null && name!=null && percent!=null && ActivityDAO.build().findByX(name.getText(), ActivityField.NAME)==null){
            Activity activityToSafe=new Activity(name.getText(),description.getText(),"",Integer.valueOf(percent.getText()), SelectedSubject.getInstance().getCurrentSubject());
            if (ActivityDAO.build().save(activityToSafe)!=null) {
                SelectedSubject.getInstance().getCurrentSubject().addActivity(activityToSafe);
                JavaFXUtils.showConfirmAlert("ACTIVITY CREATED CORRECTLY","Activity created");
                App.currentController.changeScene(Scenes.SELECTEDSUBJECT,null);
            }else {
                JavaFXUtils.showErrorAlert("ERROR CREATING ACTIVITY","");
            }
        }else {
            JavaFXUtils.showErrorAlert("ERROR CREATING ACTIVITY","All fields are not completed or activity is just in database");
        }
    }
}
