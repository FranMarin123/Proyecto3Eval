package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.singleton.TeacherSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherLoggedFirstController extends Controller implements Initializable {
    @FXML
    private Button createSubject;

    @FXML
    private Button selectSubject;

    @FXML
    private Button removeSubject;

    @FXML
    private Button modifyTeacher;

    @FXML
    private Button deleteTeacher;

    @FXML
    private Button logout;

    @FXML
    private Text welcomePrint;

    @Override
    public void onOpen(Object input) throws IOException {
        welcomePrint.setText("Welcome "+TeacherSession.getInstance().getCurrentTeacher().getName());
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void logoutButton() throws IOException {
        TeacherSession.closeSession();
        File cookie = new File("userSigned");
        if (cookie.exists()) {
            cookie.delete();
        }
        App.currentController.changeScene(Scenes.PRINCIPAL, null);
    }

    public void createSubjectButton() throws IOException {
        App.currentController.changeScene(Scenes.CREATESUBJECT, null);
    }

    public void selectSubjectButton() throws IOException {
        App.currentController.changeScene(Scenes.TEACHERSELECTSUBJECT, null);
    }

    public void deleteSubjectButton() throws IOException {
        App.currentController.changeScene(Scenes.DELETESUBJECT, null);
    }

    public void modifyTeacherButton() throws IOException {
        App.currentController.changeScene(Scenes.MODIFYTEACHER, null);
    }

    public void deleteTeacherButton() throws IOException {
        App.currentController.changeScene(Scenes.DELETETEACHER, null);
    }
}
