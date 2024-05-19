package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.ActivityDAO;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.enums.ActivityField;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.utils.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoveActivityController extends Controller implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private ImageView back;

    @Override
    public void onOpen(Object input) throws IOException {

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

    public void removeButton() throws IOException {
        if (name!=null && ActivityDAO.build().findByX(name.getText(), ActivityField.NAME)!=null){
            Activity activityToDelete=ActivityDAO.build().findByX(name.getText(), ActivityField.NAME);
            if (ActivityDAO.build().delete(activityToDelete)!=null) {
                SelectedSubject.getInstance().getCurrentSubject().removeActivity(activityToDelete);
                JavaFXUtils.showConfirmAlert("ACTIVITY ELIMINATED CORRECTLY","Activity eliminated");
                App.currentController.changeScene(Scenes.SELECTEDSUBJECT,null);
            }else {
                JavaFXUtils.showErrorAlert("ERROR DELETING ACTIVITY","");
            }
        }else {
            JavaFXUtils.showErrorAlert("ERROR DELETING ACTIVITY","All fields are not completed");
        }
    }
}
