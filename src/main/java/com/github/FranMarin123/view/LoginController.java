package com.github.FranMarin123.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.entity.User;
import com.github.FranMarin123.model.enums.UserField;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.model.singleton.TeacherSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.utils.Serializator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController extends Controller implements Initializable {



    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField dni;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private CheckBox stayLogged;

    @Override
    public void onOpen(Object input) throws IOException {
        User userSigned=(User) Serializator.deserializeObject("userSigned");
        if (userSigned instanceof Teacher) {
            TeacherSession.getInstance((Teacher) userSigned);
        } else if (userSigned instanceof Student) {
            StudentSession.getInstance((Student) userSigned);
        }
        if (TeacherSession.getInstance()!=null){
            App.currentController.changeScene(Scenes.TEACHERFIRST,null);;
        }
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean proveLogin(){
        boolean result=false;
        User userToProve=new User("",dni.getText(),"",password.getText(),"");
        if (TeacherDAO.build().findByX(userToProve.getDni(), UserField.DNI)!=null){
            Teacher teacherDB=TeacherDAO.build().findByX(userToProve.getDni(), UserField.DNI);
            if (teacherDB.getDni().equals(userToProve.getDni()) && teacherDB.getPass().equals(userToProve.getPass())){
                TeacherSession.getInstance(teacherDB);
                result=true;
            }else {
                JavaFXUtils.showAlert("LOGIN ERROR", "Incorrect Password");
            }
        } else if (StudentDAO.build().findByX(userToProve.getDni(), UserField.DNI)!=null) {
            Student studentDB=StudentDAO.build().findByX(userToProve.getDni(), UserField.DNI);
            if (studentDB.getDni().equals(userToProve.getDni()) && studentDB.getPass().equals(userToProve.getPass())){
                StudentSession.getInstance(studentDB);
                result=true;
            }else {
                JavaFXUtils.showAlert("LOGIN ERROR", "Incorrect Password");
            }
        }else {
            JavaFXUtils.showAlert("LOGIN ERROR", "We can´t found a teacher or student with this DNI");
        }
        if (result){
            generateCookie();
        }
        return result;
    }

    public void generateCookie(){
        if (stayLogged.isSelected() && TeacherSession.getInstance()!=null){
            Serializator.serializeObject(TeacherSession.getInstance().getCurrentTeacher(),"userSigned");
        } else if (stayLogged.isSelected() && StudentSession.getInstance()!=null) {
            Serializator.serializeObject(StudentSession.getInstance().getCurrentStudent(),"userSigned");
        }
    }

    public void loginClick() throws IOException {
        if (proveLogin()){
            App.currentController.changeScene(Scenes.TEACHERFIRST,null);
        }
    }

}