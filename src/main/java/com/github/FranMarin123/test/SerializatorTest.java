package com.github.FranMarin123.test;

import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.entity.User;
import com.github.FranMarin123.model.enums.UserField;
import com.github.FranMarin123.utils.Serializator;

public class SerializatorTest {
    public static void main(String[] args) {
        /*Serializator.serializeObject(TeacherDAO.build().findByX("Juan", UserField.NAME),"prueba");
        User test=Serializator.deserializeObject("prueba");
        if (test instanceof Teacher) {
            System.out.println(test);
        }*/
        //Teacher test=Serializator.deserializeObject("userSigned");
        //System.out.println(test);

        String hola="2";
        String hola2="6";
        int holan=Integer.valueOf(hola);
        System.out.println(holan);
    }
}
