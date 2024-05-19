package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.model.singleton.TeacherSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.github.FranMarin123.view.Scenes.STUDENTSELECTSUBJECT;

public class StudentLoggedController extends Controller implements Initializable {

    @FXML
    private Text welcomePrint;

    @Override
    public void onOpen(Object input) throws IOException {
        welcomePrint.setText("Welcome "+ StudentSession.getInstance().getCurrentStudent().getName());
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void logoutButton() throws IOException {
        StudentSession.closeSession();
        File cookie = new File("userSigned");
        if (cookie.exists()) {
            cookie.delete();
        }
        App.currentController.changeScene(Scenes.PRINCIPAL, null);
    }

    public void selectSubject() throws IOException {
        App.currentController.changeScene(Scenes.STUDENTSELECTSUBJECT,null);
    }

    public void modifyStudent() throws IOException {
        App.currentController.changeScene(Scenes.MODIFYSTUDENT,null);
    }

    public void deleteStudent() throws IOException {
        App.currentController.changeScene(Scenes.DELETESTUDENT,null);
    }

}
