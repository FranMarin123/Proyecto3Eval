package com.github.FranMarin123.model.entity;


import java.util.Objects;

public class Student extends User{
    private int id;
    private Inscription inscription;
    private Subject subject;

    public Student(String dni, String name, String mail, String pass, String photo,Inscription inscription, Subject subject) {
        this.dni = dni;
        this.name = name;
        this.mail = mail;
        setPass(pass);
        setPhoto(photo);
        this.inscription=inscription;
        this.subject=subject;
    }

    public Student() {
        this("", "", "", "", /*Ruta imagen por defecto*/"",null,null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "idUser=" + id +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
