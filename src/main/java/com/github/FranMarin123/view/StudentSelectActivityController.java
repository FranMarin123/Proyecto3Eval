package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.ActivityDAO;
import com.github.FranMarin123.model.dao.InscriptionDAO;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.entity.Inscription;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.enums.ActivityField;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.STUDENTFIRST,null);
    }

    public void enteringBackImg() {
        back.setFitWidth(45);
        back.setFitHeight(25);
    }

    public void exitingBackImg() {
        back.setFitHeight(52);
        back.setFitWidth(32);
    }
}
