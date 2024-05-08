package com.github.FranMarin123.model.entity;

import java.util.Objects;

public class Teacher extends User{
    private int id;

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
                "idTeacher=" + id +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
