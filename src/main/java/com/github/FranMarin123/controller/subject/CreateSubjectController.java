package com.github.FranMarin123.controller.subject;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.singleton.TeacherSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateSubjectController extends Controller implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private TextField hours = new TextField();

    @FXML
    private ImageView back;

    /**
     * This method makes hours textfield only can get numbers
     * @param input
     * @throws IOException
     */
    @Override
    public void onOpen(Object input) throws IOException {
        hours.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method create a subject with information obtained with textfields
     * @return
     */
    public boolean registerSubject(){
        boolean result=false;
        if (name.getText()!=null && !name.getText().isEmpty() && Integer.valueOf(hours.getText())>0
                && SubjectDAO.build().findByX(name.getText(),"name")==null){
            Subject subjectToCreate=new Subject(name.getText(),Integer.valueOf(hours.getText()), TeacherSession.getInstance().getCurrentTeacher());
            SubjectDAO.build().save(subjectToCreate);
            JavaFXUtils.showConfirmAlert("SUBJECT CREATED CORRECTLY","Subject created");
            result=true;
            TeacherSession.getInstance().refreshSubjects();
        } else {
            JavaFXUtils.showErrorAlert("ERROR CREATING SUBJECT","Cant´t create this subject, wrong name, hours or is just in database");
        }
        return result;
    }

    public void createButton() throws IOException {
        if (registerSubject()){
            App.currentController.changeScene(Scenes.TEACHERFIRST,null);
        }
    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.TEACHERFIRST,null);
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
