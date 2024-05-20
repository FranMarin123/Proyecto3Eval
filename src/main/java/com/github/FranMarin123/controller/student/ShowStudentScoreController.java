package com.github.FranMarin123.controller.student;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.singleton.StudentSession;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowStudentScoreController extends Controller implements Initializable {

    @FXML
    private Text scoreShow;

    @FXML
    private ImageView back;

    /**
     * This method print in a Text a string with information about score of a student in a activity
     * @param input
     * @throws IOException
     */
    @Override
    public void onOpen(Object input) throws IOException {
        scoreShow.setText("Score of "+ StudentSession.getInstance().getCurrentStudent().getName()+ "\nis "
                +StudentSession.getInstance().getCurrentStudent().getInscription().getNota()+" \nin activity "+
                StudentSession.getInstance().getCurrentStudent().getInscription().getActivity().getName());
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
