package com.github.FranMarin123.test.db;

import com.github.FranMarin123.model.connection.ConnectionProperties;
import com.github.FranMarin123.utils.XMLManager;

public class connectionLoad {
    public static void main(String[] args) {
        ConnectionProperties c = XMLManager.readXML(new ConnectionProperties(),"connection.xml");
        System.out.println(c);
    }
}
