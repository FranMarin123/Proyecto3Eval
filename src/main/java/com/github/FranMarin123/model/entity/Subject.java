package com.github.FranMarin123.model.entity;

import java.util.Objects;

public class Subject {
    private int idSubject;
    private String name;
    private int hours;
    private Teacher teacher;

    public Subject(int idSubject, String name, int hours, Teacher teacher) {
        this.idSubject = idSubject;
        this.name = name;
        this.hours = hours;
        this.teacher = teacher;
    }

    public Subject() {
        this(-1,"",-1,new Teacher());
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Subject subject = (Subject) object;
        return idSubject == subject.idSubject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubject);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "idSubject=" + idSubject +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", teacher=" + teacher +
                '}';
    }
}
