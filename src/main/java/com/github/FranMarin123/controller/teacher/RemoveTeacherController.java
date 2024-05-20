package com.github.FranMarin123.controller.teacher;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.model.singleton.TeacherSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoveTeacherController extends Controller implements Initializable {

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
     * This method delete a teacher from database and close actual session
     * @throws IOException
     */
    public void confirmationClick() throws IOException {
        TeacherDAO.build().delete(TeacherSession.getInstance().getCurrentTeacher());
        TeacherSession.closeSession();
        JavaFXUtils.showConfirmAlert("TEACHER DELETED","Teacher deleted correctly");
        App.currentController.changeScene(Scenes.PRINCIPAL,null);
    }

    public void backClick() throws IOException {
        SelectedSubject.removeSubject();
        App.currentController.changeScene(Scenes.TEACHERFIRST,null);
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
