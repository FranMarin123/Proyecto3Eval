package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.singleton.StudentSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowStudentSubjectController extends Controller implements Initializable {

    @FXML
    private Text information = new Text();

    @FXML
    private Text dni = new Text();

    @FXML
    private Text name = new Text();

    @FXML
    private Text mail = new Text();

    @FXML
    private ImageView back;

    @Override
    public void onOpen(Object input) throws IOException {
        information.setText("STUDENT INFORMATION:");
        dni.setText("DNI: " + StudentSession.getInstance().getCurrentStudent().getDni());
        name.setText("Name: " + StudentSession.getInstance().getCurrentStudent().getName());
        mail.setText("Mail: " + StudentSession.getInstance().getCurrentStudent().getMail());
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.SELECTEDSUBJECT, null);
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
