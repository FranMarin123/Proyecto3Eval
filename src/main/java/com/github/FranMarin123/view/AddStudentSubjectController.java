package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.enums.UserField;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.utils.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentSubjectController extends Controller implements Initializable {

    @FXML
    private TextField dniStudent;

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

    public void confirmClick() throws IOException {
        if (dniStudent!=null) {
            if (SelectedSubject.getInstance().getCurrentSubject().addStudent(StudentDAO.build().findByX(dniStudent.getText(), UserField.DNI))) {
                SubjectDAO.build().saveStudentSubject(StudentDAO.build().findByX(dniStudent.getText(), UserField.DNI),SelectedSubject.getInstance().getCurrentSubject());
                JavaFXUtils.showConfirmAlert("ADDED CORRECTLY", "Student added to this subject");
                App.currentController.changeScene(Scenes.SELECTEDSUBJECT,null);
            }else {
                JavaFXUtils.showErrorAlert("ERROR ADDING STUDENT", "We can´t find student with this DNI");
            }
        }else {
            JavaFXUtils.showErrorAlert("ERROR ADDING STUDENT", "Text Field empty");
        }
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
}
