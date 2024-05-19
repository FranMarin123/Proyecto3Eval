package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.model.singleton.TeacherSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyStudentController extends Controller implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField pass;

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

    public void modifyClick() throws IOException {
        Student studentToRegister = StudentSession.getInstance().getCurrentStudent();
        Student oldStudent = StudentDAO.build().findByX(studentToRegister.getDni(), UserField.DNI);
        if (name != null && !name.getText().isEmpty()) {
            studentToRegister.setName(name.getText());
        }
        if (pass != null && !pass.getText().isEmpty()) {
            studentToRegister.setPass(pass.getText());
        }
        if (mail != null && !mail.getText().isEmpty()) {
            studentToRegister.setMail(mail.getText());
        }
        if (!studentToRegister.getName().equals(oldStudent.getName())
                || !studentToRegister.getPass().equals(oldStudent.getPass())
                || !studentToRegister.getMail().equals(oldStudent.getMail())) {
            StudentDAO.build().save(studentToRegister);
            StudentSession.getInstance(studentToRegister);
            JavaFXUtils.showConfirmAlert("CORRECT MODIFY", "Student modified correctly");
            App.currentController.changeScene(Scenes.STUDENTFIRST, null);
        } else {
            JavaFXUtils.showErrorAlert("ERROR MODIFYING STUDENT", "Nothing to modify");
        }
    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.STUDENTFIRST, null);
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
