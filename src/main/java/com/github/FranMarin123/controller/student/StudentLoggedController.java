package com.github.FranMarin123.controller.student;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    /**
     * This method logout a student from the application and delete session file
     * @throws IOException
     */
    public void logoutButton() throws IOException {
        StudentSession.closeSession();
        File cookie = new File("userSigned");
        if (cookie.exists()) {
            cookie.delete();
        }
        App.currentController.changeScene(Scenes.PRINCIPAL, null);
    }

    /**
     * This method change the scene to STUDENTSELECTSUBJECT
     * @throws IOException
     */
    public void selectSubject() throws IOException {
        App.currentController.changeScene(Scenes.STUDENTSELECTSUBJECT,null);
    }

    /**
     * This method change the scene to MODIFYSTUDENT
     * @throws IOException
     */
    public void modifyStudent() throws IOException {
        App.currentController.changeScene(Scenes.MODIFYSTUDENT,null);
    }

    /**
     * This method change the scene to DELETESTUDENT
     * @throws IOException
     */
    public void deleteStudent() throws IOException {
        App.currentController.changeScene(Scenes.DELETESTUDENT,null);
    }

}
