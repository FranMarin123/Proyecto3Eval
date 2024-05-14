package com.github.FranMarin123.view;

import com.github.FranMarin123.App;
import com.github.FranMarin123.utils.XMLManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView image;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private Button exit;

    private Controller centerController;

    @Override
    public void onOpen(Object input) throws IOException {
        //changeScene(Scenes.PRINCIPAL,null);
    }

    public void changeScene(Scenes scene,Object data) throws IOException {
        View view = loadFXML(scene);
        //anchorPane.;
        this.centerController = view.controller;
        this.centerController.onOpen(data);
    }

    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getPath();
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent p = loader.load();
        Controller c = loader.getController();
        View view = new View();
        view.scene=p;
        view.controller=c;
        return view;
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
