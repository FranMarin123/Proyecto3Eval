package com.github.FranMarin123;

import com.github.FranMarin123.controller.AppController;
import com.github.FranMarin123.view.Scenes;
import com.github.FranMarin123.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    public static AppController currentController;

    @Override
    public void start(Stage stage) throws IOException {
        View view = AppController.loadFXML(Scenes.BASE);
        scene = new Scene(view.scene, 640, 480);
        currentController=(AppController) view.controller;
        currentController.onOpen(null);
        stage.setTitle("EducSoftware 1.0");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("images/logoWindow.png")));
        stage.setScene(scene);
        stage.setMinHeight(520);
        stage.setMinWidth(680);
        stage.setMaxHeight(520);
        stage.setMaxWidth(680);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        //scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }

}