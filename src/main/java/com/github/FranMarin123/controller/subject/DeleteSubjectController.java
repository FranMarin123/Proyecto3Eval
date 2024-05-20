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

    /**
     * This method change the scene to SELECTEDSUBJECT
     * @throws IOException
     */
    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.TEACHERFIRST,null);
    }

    /**
     * This method delete a subject selected
     * @throws IOException
     */
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

    /**
     * This method resize image back
     */
    public void enteringBackImg() {
        back.setFitWidth(45);
        back.setFitHeight(25);
    }

    /**
     * This method resize image back to original size
     */
    public void exitingBackImg() {
        back.setFitHeight(52);
        back.setFitWidth(32);
    }
}
