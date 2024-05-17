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
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends Controller implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private TextField dni;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField pass;

    @FXML
    private ChoiceBox<String> choiceBox=new ChoiceBox<>();

    @FXML
    private Button register;

    @Override
    public void onOpen(Object input) throws IOException {
        choiceBox.getItems().add("Teacher");
        choiceBox.getItems().add("Student");
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void registerClick() throws IOException {
        if (choiceBox.getValue()==null){
            JavaFXUtils.showAlert("ERROR CREATING STUDENT", "Type of user not selected");
        }else {
            if (choiceBox.getValue().equals("Teacher")) {
                Teacher teacherToRegister = new Teacher(name.getText(), dni.getText(), mail.getText(), pass.getText(), "images/defaultAvatar.png");
                if (TeacherDAO.build().findByX(teacherToRegister.getDni(), UserField.DNI) == null
                        && TeacherDAO.build().findByX(teacherToRegister.getMail(), UserField.MAIL) == null) {
                    TeacherDAO.build().save(teacherToRegister);
                    TeacherSession.getInstance(teacherToRegister);
                    App.currentController.changeScene(Scenes.TEACHERFIRST,null);
                } else {
                    JavaFXUtils.showAlert("ERROR CREATING TEACHER", "User not created because DNI or Mail is in use");
                }
            } else if (choiceBox.getValue().equals("Student")) {
                Student studentToRegister = new Student(name.getText(), dni.getText(), mail.getText(), pass.getText(), "images/defaultAvatar.png");
                if (StudentDAO.build().findByX(studentToRegister.getDni(), UserField.DNI) == null &&
                        StudentDAO.build().findByX(studentToRegister.getMail(), UserField.MAIL) == null) {
                    StudentDAO.build().save(studentToRegister);
                    StudentSession.getInstance(studentToRegister);
                } else {
                    JavaFXUtils.showAlert("ERROR CREATING STUDENT", "User not created because DNI or Mail is in use");
                }
            }
        }
    }

}
