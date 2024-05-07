package com.github.FranMarin123.test;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Teacher;

public class DeleteTest {
    public static void main(String[] args) {
        /*
        Student toRemove=new Student("232342d","","","","",null,null);
        StudentDAO studentDAO=new StudentDAO();
        System.out.println(studentDAO.delete(toRemove));
        */
        Teacher toRemove=new Teacher("","232342d","","","");
        TeacherDAO teacherDAO=new TeacherDAO();
        System.out.println(teacherDAO.delete(toRemove));


    }
}
