package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;
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

public class ModifyTeacherController extends Controller implements Initializable {

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
        Teacher teacherToRegister = TeacherSession.getInstance().getCurrentTeacher();
        Teacher oldTeacher=TeacherDAO.build().findByX(teacherToRegister.getDni(),UserField.DNI);
        if (name!=null && !name.getText().isEmpty()) {
            teacherToRegister.setName(name.getText());
        }
        if (pass!=null && !pass.getText().isEmpty()){
            teacherToRegister.setPass(pass.getText());
        }
        if (mail!=null && !mail.getText().isEmpty()){
            teacherToRegister.setMail(mail.getText());
        }
        if (!teacherToRegister.getName().equals(oldTeacher.getName())
                || !teacherToRegister.getPass().equals(oldTeacher.getPass())
                || !teacherToRegister.getMail().equals(oldTeacher.getMail())) {
            TeacherDAO.build().save(teacherToRegister);
            TeacherSession.getInstance(teacherToRegister);
            JavaFXUtils.showConfirmAlert("CORRECT MODIFY","Teacher modified correctly");
            App.currentController.changeScene(Scenes.TEACHERFIRST, null);
        } else {
            JavaFXUtils.showErrorAlert("ERROR MODIFYING TEACHER", "Nothing to modify");
        }
    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.TEACHERFIRST, null);
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
