package com.github.FranMarin123;

import com.github.FranMarin123.view.AppController;
import com.github.FranMarin123.view.Scenes;
import com.github.FranMarin123.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static com.github.FranMarin123.view.AppController.loadFXML;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    public static AppController currentController;

    @Override
    public void start(Stage stage) throws IOException {
        View view = AppController.loadFXML(Scenes.PRINCIPAL);
        scene = new Scene(view.scene, 640, 480);
        currentController=(AppController) view.controller;
        currentController.onOpen(null);
        stage.setTitle("EducSoftware 1.0");
        stage.getIcons().add(new Image("src/main/resources/com/github/FranMarin123/images/logoWindow.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        //scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}