package com.github.FranMarin123.model.entity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Teacher extends User{

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Teacher teacher = (Teacher) object;
        return idUser == teacher.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idTeacher=" + idUser +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
