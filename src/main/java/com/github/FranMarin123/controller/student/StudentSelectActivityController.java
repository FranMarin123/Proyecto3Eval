package com.github.FranMarin123.controller.student;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.dao.ActivityDAO;
import com.github.FranMarin123.model.dao.InscriptionDAO;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.enums.ActivityField;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentSelectActivityController extends Controller implements Initializable {

    @FXML
    private TextField activityName;

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

    /**
     * This method get from a textfield a name of a activity and it finds this subject in database
     * @throws IOException
     */
    public void selectButton() throws IOException {
        if (activityName!=null) {
            Activity actToSelect = ActivityDAO.build().findByX(activityName.getText(), ActivityField.NAME);
            if (InscriptionDAO.build().find(StudentSession.getInstance().getCurrentStudent(), actToSelect)!=null){
                StudentSession.getInstance().getCurrentStudent().setInscription(InscriptionDAO.build().find(StudentSession.getInstance().getCurrentStudent(), actToSelect));
                JavaFXUtils.showConfirmAlert("ACTIVITY SELECTED","Activity selected correctly");
                App.currentController.changeScene(Scenes.SHOWSTUDENTSCORE,null);
            }
        }
    }

    /**
     * This method change the scene to STUDENTFIRST
     * @throws IOException
     */
    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.STUDENTFIRST,null);
    }

    /**
     * This method resize image back
     */
    public void enteringBackImg() {
        back.setFitWidth(45);
        back.setFitHeight(25);
    }

    /**
     * This method resize image back to original size
     */
    public void exitingBackImg() {
        back.setFitHeight(52);
        back.setFitWidth(32);
    }
}
