package com.github.FranMarin123.controller.subject;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.singleton.SelectedSubject;
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

public class StudentBrowserController extends Controller implements Initializable {

    @FXML
    private ImageView back;

    @FXML
    private TextField dniStudent;

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
     * This method change the scene to SELECTEDSUBJECT
     * @throws IOException
     */
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

    /**
     * This method gets a dni with a textfield and he finds a student with this dni in database
     * @throws IOException
     */
    public void confirmClick() throws IOException {
        if (dniStudent!=null) {
            if (SelectedSubject.getInstance().getCurrentSubject().getStudents().containsKey(dniStudent.getText())) {
                StudentSession.getInstance(SelectedSubject.getInstance().getCurrentSubject().getStudents().get(dniStudent.getText()));
                JavaFXUtils.showConfirmAlert("STUDENT SELECTED CORRECTLY", "Student selected correctly");
                App.currentController.changeScene(Scenes.SHOWSTUDENTSUBJECT,null);
            }else {
                JavaFXUtils.showErrorAlert("ERROR STUDENT NOT FOUND", "We can´t find student with this DNI");
            }
        }else {
            JavaFXUtils.showErrorAlert("ERROR SELECTING STUDENT", "Text Field empty");
        }
    }
}
