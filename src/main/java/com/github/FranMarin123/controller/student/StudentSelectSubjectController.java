package com.github.FranMarin123.controller.student;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentSelectSubjectController extends Controller implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox=new ChoiceBox<>();

    @FXML
    private ImageView back;


    /**
     * This method fill choicebox with all subjects of the student
     * @param input
     * @throws IOException
     */
    @Override
    public void onOpen(Object input) throws IOException {
        for (Subject s: StudentSession.getInstance().getCurrentStudent().getSubjects()){
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
     * This method change the scene to STUDENTFIRST
     * @throws IOException
     */
    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.STUDENTFIRST,null);
    }

    /**
     * This method get from a choicebox selection a subject
     * @throws IOException
     */
    public void selectSubjectButton() throws IOException {
        if (choiceBox!=null && choiceBox.getValue()!=null){
            SelectedSubject.getInstance(SubjectDAO.build().findByX(choiceBox.getValue(),"name"));
            JavaFXUtils.showConfirmAlert("SUBJECT SELECTED CORRECTLY", "Subject selected");
            App.currentController.changeScene(Scenes.STUDENTSELECTACTIVITY,null);
        }else {
            JavaFXUtils.showErrorAlert("ERROR IN SELECTION", "Subject not selected");
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
