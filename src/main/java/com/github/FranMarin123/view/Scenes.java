package com.github.FranMarin123.view;

public enum Scenes {
    BASE("view/base.fxml"),
    PRINCIPAL("view/principalScreen.fxml"),
    LOGIN("view/loginScreen.fxml"),
    REGISTER("view/registerScreen.fxml"),
    TEACHERFIRST("view/teacherLoggedFirstScreen.fxml"),
    CREATESUBJECT("view/createSubjectScreen.fxml");

    private String path;

    Scenes(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
