package com.github.FranMarin123.controller.student;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoveStudentController extends Controller implements Initializable {

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

    public void confirmationClick() throws IOException {
        StudentDAO.build().delete(StudentSession.getInstance().getCurrentStudent());
        StudentSession.closeSession();
        JavaFXUtils.showConfirmAlert("STUDENT DELETED","Student deleted correctly");
        App.currentController.changeScene(Scenes.PRINCIPAL,null);
    }

    /**
     * This method change the scene to SELECTEDSUBJECT
     * @throws IOException
     */
    public void backClick() throws IOException {
        SelectedSubject.removeSubject();
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
