package com.github.FranMarin123.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Subject {
    private int idSubject;
    private String name;
    private int hours;
    private Teacher teacher;
    private Set<Activity> activities;
    private Set<Student> students;

    public Subject(int idSubject, String name, int hours, Teacher teacher, Set<Activity> activities,Set<Student> students) {
        this.idSubject = idSubject;
        this.name = name;
        this.hours = hours;
        this.teacher = teacher;
        this.activities = activities;
        this.students = students;
    }

    public Subject() {
        this(-1, "", -1, new Teacher(), new HashSet<Activity>(),new HashSet<Student>());
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

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
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
