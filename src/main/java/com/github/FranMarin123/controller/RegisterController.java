package com.github.FranMarin123.controller;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.model.singleton.TeacherSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();

    @FXML
    private Button register;

    @FXML
    private ImageView back;

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

    /**
     * This method detect if information to create a new teacher or student are correct and creates it
     * @throws IOException
     */
    public void registerClick() throws IOException {
        if (choiceBox.getValue() == null) {
            JavaFXUtils.showErrorAlert("ERROR CREATING USER", "Type of user not selected");
        } else {
            if (!Pattern.compile("\\d{8}[a-zA-Z]").matcher(dni.getText()).matches()
                    && !Pattern.compile("[a-zA-Z]+").matcher(name.getText()).matches()
                    && !Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matcher(mail.getText()).matches()){
                JavaFXUtils.showErrorAlert("ERROR CREATING USER", "Please check text fields");
            }else{
                if (choiceBox.getValue().equals("Teacher")) {
                    Teacher teacherToRegister = new Teacher(name.getText(), dni.getText(), mail.getText(), pass.getText(), "images/defaultAvatar.png");
                    if (TeacherDAO.build().findByX(teacherToRegister.getDni(), UserField.DNI) == null
                            && TeacherDAO.build().findByX(teacherToRegister.getMail(), UserField.MAIL) == null) {
                        TeacherDAO.build().save(teacherToRegister);
                        TeacherSession.getInstance(teacherToRegister);
                        App.currentController.changeScene(Scenes.TEACHERFIRST, null);
                    } else {
                        JavaFXUtils.showErrorAlert("ERROR CREATING TEACHER", "User not created because DNI or Mail is in use");
                    }
                } else if (choiceBox.getValue().equals("Student")) {
                    Student studentToRegister = new Student(dni.getText(), name.getText(), mail.getText(), pass.getText(), "images/defaultAvatar.png");
                    if (StudentDAO.build().findByX(studentToRegister.getDni(), UserField.DNI) == null &&
                            StudentDAO.build().findByX(studentToRegister.getMail(), UserField.MAIL) == null) {
                        StudentDAO.build().save(studentToRegister);
                        StudentSession.getInstance(studentToRegister);
                        App.currentController.changeScene(Scenes.STUDENTFIRST, null);
                    } else {
                        JavaFXUtils.showErrorAlert("ERROR CREATING STUDENT", "User not created because DNI or Mail is in use");
                    }
                }
            }
        }
    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.PRINCIPAL, null);
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
