package com.github.FranMarin123.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Teacher extends User implements Serializable {
    private int id;
    private Set<Subject> subjects;

    public Teacher(String name, String dni, String mail, String pass, String photo, HashSet<Subject> subjects) {
        this.name = name;
        this.dni = dni;
        this.mail = mail;
        super.setPass(pass);
        super.setPhoto(photo);
        this.subjects=subjects;
    }

    public Teacher(String name, String dni, String mail, String pass, String photo) {
        this.name = name;
        this.dni = dni;
        this.mail = mail;
        super.setPass(pass);
        super.setPhoto(photo);
    }


    public Teacher(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int idUser) {
        this.id = idUser;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public void removeSubject(Subject subjectToRemove){
        if (subjects!=null){
            subjects.remove(subjectToRemove);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Teacher teacher = (Teacher) object;
        return id == teacher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", subjects=" + subjects +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", mail='" + mail + '\'' +
                ", pass='" + pass + '\'' +
                ", photo=" + photo.toString() +
                '}';
    }
}
