package com.github.FranMarin123.test;

import com.github.FranMarin123.model.connection.ConnectionProperties;
import com.github.FranMarin123.utils.XMLManager;

public class connectionSave {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","PROYECTO3EVAL","root","usuario");
        XMLManager.writeXML(c,"connection.xml");
    }
}
