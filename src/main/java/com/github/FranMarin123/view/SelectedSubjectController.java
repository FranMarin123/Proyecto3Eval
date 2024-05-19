package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectedSubjectController extends Controller implements Initializable {

    @FXML
    private Button addStudent;

    @FXML
    private Button createActivity;

    @FXML
    private Button removeStudent;

    @FXML
    private Button removeActivity;

    @FXML
    private Button browseStudent;

    @FXML
    private ImageView back;

    @FXML
    private Text subjectText=new Text();


    @Override
    public void onOpen(Object input) throws IOException {
        subjectText.setText(SelectedSubject.getInstance().getCurrentSubject().getName());
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void backClick() throws IOException {
        SelectedSubject.removeSubject();
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

    public void addStudentClick() throws IOException {
        App.currentController.changeScene(Scenes.ADDSTUDENTSUBJECT,null);
    }

    public void removeStudentClick() throws IOException {
        App.currentController.changeScene(Scenes.REMOVESTUDENTSUBJECT,null);
    }

    public void addActivityClick() throws IOException {
        App.currentController.changeScene(Scenes.ADDACTIVITY,null);
    }

    public void removeActivityClick() throws IOException {
        App.currentController.changeScene(Scenes.DELETEACTIVITY,null);
    }

    public void browseStudentClick() throws IOException {
        App.currentController.changeScene(Scenes.BROWSESTUDENT, null);
    }

    public void scoreStudentClick() throws IOException {
        App.currentController.changeScene(Scenes.STUDENTSCORE, null);
    }
}
