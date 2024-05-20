package com.github.FranMarin123.controller.student;

import com.github.FranMarin123.App;
import com.github.FranMarin123.model.dao.ActivityDAO;
import com.github.FranMarin123.model.dao.InscriptionDAO;
import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.entity.Inscription;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.enums.ActivityField;
import com.github.FranMarin123.model.enums.UserField;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.controller.Controller;
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

public class AddStudentScoreController extends Controller implements Initializable {

    @FXML
    private TextField studentDNI;

    @FXML
    private TextField activityName;

    @FXML
    private TextField score;

    @FXML
    private ImageView back;

    @Override
    public void onOpen(Object input) throws IOException {
        score.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method add to a student a score obtained with a textfield
     */
    public void confirmClick(){
        if (studentDNI!=null && score!=null && activityName!=null && Integer.valueOf(score.getText())>-1 && Integer.valueOf(score.getText())<11){
            Student student=StudentDAO.build().findByX(studentDNI.getText(), UserField.DNI);
            Activity activity=ActivityDAO.build().findByX(activityName.getText(), ActivityField.NAME);
            if (student!=null && activity!=null){
                if (InscriptionDAO.build().find(student,activity)==null) {
                    Inscription inscriptionToAdd = new Inscription(student, activity, Integer.valueOf(score.getText()));
                    InscriptionDAO.build().save(inscriptionToAdd);
                    JavaFXUtils.showConfirmAlert("SCORE ADDED CORRECTLY","Score for this student in this activity added");
                }else {
                    InscriptionDAO.build().addScore(new Inscription(student, activity, Integer.valueOf(score.getText())));
                    JavaFXUtils.showConfirmAlert("SCORE UPDATED CORRECTLY", "Score for this student updated");
                }
            }else {
                JavaFXUtils.showErrorAlert("WRONG STUDENT OR ACTIVITY","We can´t found student or activity");
            }
        }else {
            JavaFXUtils.showErrorAlert("FIELD ERROR","Field error, please check fields and try again");
        }
    }

    /**
     * This method change the scene to SELECTEDSUBJECT
     * @throws IOException
     */
    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.SELECTEDSUBJECT, null);
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
