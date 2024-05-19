package com.github.FranMarin123.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Inscription implements Serializable {
    private Student student;
    private Activity activity;
    private int nota;

    public Inscription(Student student, Activity activity, int nota) {
        this.student = student;
        this.activity = activity;
        this.nota = nota;
    }

    public Inscription() {
        this(new Student(),new Activity(),-1);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Inscription that = (Inscription) object;
        return Objects.equals(student, that.student) && Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, activity);
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "student=" + student +
                ", activity=" + activity +
                ", nota=" + nota +
                '}';
    }
}
