package com.github.FranMarin123.controller.subject;

import com.github.FranMarin123.App;
import com.github.FranMarin123.controller.Controller;
import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.enums.UserField;
import com.github.FranMarin123.model.singleton.SelectedSubject;
import com.github.FranMarin123.utils.JavaFXUtils;
import com.github.FranMarin123.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoveStudentSubjectController extends Controller implements Initializable {

    @FXML
    private TextField dniToRemove;

    @FXML
    private ImageView back;

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method remove a selected student from a selected subject
     * @throws IOException
     */
    public void removeButton() throws IOException {
        if (dniToRemove!=null && SelectedSubject.getInstance().getCurrentSubject().getStudents().containsKey(dniToRemove.getText())){
            SubjectDAO.build().removeStudentFromSubject(StudentDAO.build().findByX(dniToRemove.getText(), UserField.DNI),
                    SelectedSubject.getInstance().getCurrentSubject());
            JavaFXUtils.showConfirmAlert("DELETED CORRECTLY", "Student deleted to this subject");
            App.currentController.changeScene(Scenes.SELECTEDSUBJECT,null);
        }else {
            JavaFXUtils.showErrorAlert("ERROR DELETING STUDENT", "We can´t find student with this DNI");
        }
    }

    /**
     * This method change the scene to SELECTEDSUBJECT
     * @throws IOException
     */
    public void backClick() throws IOException {
        App.currentController.changeScene(Scenes.SELECTEDSUBJECT,null);
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
