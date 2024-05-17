package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.model.singleton.TeacherSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherSelectSubjectController extends Controller implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox=new ChoiceBox<>();

    @FXML
    private ImageView back;


    @Override
    public void onOpen(Object input) throws IOException {
        for (Subject s: TeacherSession.getInstance().getCurrentTeacher().getSubjects()){
            choiceBox.getItems().add(s.getName());
        }
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.TEACHERFIRST,null);
    }

    public void selectSubjectButton(){
        if (choiceBox!=null && choiceBox.getValue()!=null){
            SelectedSubject.getInstance(SubjectDAO.build().findByX(choiceBox.getValue(),"name"));
            JavaFXUtils.showConfirmAlert("SUBJECT SELECTED CORRECTLY", "Subject selected");
        }else {
            JavaFXUtils.showErrorAlert("ERROR IN SELECTION", "Subject not selected");
        }
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
