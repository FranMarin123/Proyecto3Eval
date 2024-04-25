package com.github.FranMarin123.model.entity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Student {
    private int idUser;
    private String dni;
    private String name;
    private String mail;
    private String pass;
    private File photo;
    private Inscription inscription;
    private Subject subject;

    public Student(int idUser, String dni, String name, String mail, String pass, String photo,Inscription inscription, Subject subject) {
        this.idUser = idUser;
        this.dni = dni;
        this.name = name;
        this.mail = mail;
        setPass(pass);
        setPhoto(photo);
        this.inscription=inscription;
        this.subject=subject;
    }

    public Student() {
        this(-1, "", "", "", "", /*Ruta imagen por defecto*/"",new Inscription(),new Subject());
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return idUser == student.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }

    @Override
    public String toString() {
        return "Student{" +
                "idUser=" + idUser +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
