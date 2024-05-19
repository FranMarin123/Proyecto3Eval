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

public class DeleteSubjectController extends Controller implements Initializable {

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

    public void deleteSubjectButton() throws IOException {
        if (choiceBox!=null && choiceBox.getValue()!=null){
            SubjectDAO.build().delete(SubjectDAO.build().findByX(choiceBox.getValue(),"name"));
            TeacherSession.getInstance().refreshSubjects();
            JavaFXUtils.showConfirmAlert("SUBJECT DELETED CORRECTLY", "Subject deleted");
            App.currentController.changeScene(Scenes.TEACHERFIRST,null);
        }else {
            JavaFXUtils.showErrorAlert("DELETE ERROR", "Subject not deleted");
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
