package com.github.FranMarin123.view;

public enum Scenes {
    BASE("view/base.fxml"),
    PRINCIPAL("view/principalScreen.fxml");

    private String path;

    Scenes(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
