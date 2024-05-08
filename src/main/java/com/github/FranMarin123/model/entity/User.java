package com.github.FranMarin123.model.entity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    protected String name;
    protected String dni;
    protected String mail;
    protected String pass;
    protected File photo;

    public User(String name, String dni, String mail, String pass, String photo) {
        this.name = name;
        this.dni = dni;
        this.mail = mail;
        setPass(pass);
        setPhoto(photo);
    }

    public User(){
        this("", "", "", "", /*Ruta imagen por defecto*/"");
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashedBytes = digest.digest(pass.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            String hashedPassword = stringBuilder.toString();

            this.pass = hashedPassword;
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(String photoURL) {
        this.photo = new File(photoURL);
    }
}
