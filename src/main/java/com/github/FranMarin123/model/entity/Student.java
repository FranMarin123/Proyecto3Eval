package com.github.FranMarin123.model.entity;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student extends User {
    private int id;
    private Inscription inscription;
    private Set<Subject> subjects;

    public Student(String dni, String name, String mail, String pass, String photo, Inscription inscription, Set<Subject> subjects) {
        this.dni = dni;
        this.name = name;
        this.mail = mail;
        setPass(pass);
        setPhoto(photo);
        this.inscription = inscription;
        this.subjects = subjects;
    }

    public Student(String dni, String name, String mail, String pass, String photo, Inscription inscription) {
        this.dni = dni;
        this.name = name;
        this.mail = mail;
        setPass(pass);
        setPhoto(photo);
        this.inscription = inscription;
    }

    public Student() {
        this("", "", "", "", /*Ruta imagen por defecto*/"", null, null);
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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
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
                "id=" + id +
                ", inscription=" + inscription +
                ", subjects=" + subjects +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", mail='" + mail + '\'' +
                ", pass='" + pass + '\'' +
                ", photo=" + photo +
                '}';
    }

    public boolean addSubject(Subject subjectToAdd) {
        boolean result = false;
        if (subjects == null) {
            subjects = new HashSet<>();
        }
        if (subjects.add(subjectToAdd)) {
            result = true;
        }
        return result;
    }

    public boolean removeSubject(Subject subjectToAdd) {
        boolean result = false;
        if (subjects != null && subjects.add(subjectToAdd)) {
            result = true;
        }

        return result;
    }

}
