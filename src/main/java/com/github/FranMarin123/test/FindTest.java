package com.github.FranMarin123.test;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;

public class FindTest {
    public static void main(String[] args) {
        /*
        StudentDAO sd=new StudentDAO();
        Student sToFind=sd.findByX("Juanse", UserField.NAME);
        System.out.println(sToFind);
        */
        TeacherDAO td=new TeacherDAO();
        Teacher tToFind=td.findByX("Juanse", UserField.NAME);
        System.out.println(tToFind);
    }
}
