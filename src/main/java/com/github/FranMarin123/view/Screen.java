package com.github.FranMarin123.view;

public enum Screen {
    PRINCIPAL("view/principalScreen.fxml");

    private String path;

    Screen(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
