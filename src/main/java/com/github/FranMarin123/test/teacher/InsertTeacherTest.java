package com.github.FranMarin123.test.teacher;

import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Teacher;

public class InsertTeacherTest {
    public static void main(String[] args) {
        Teacher teacherToInsert=new Teacher("Antonio","12345678A","antonio@gmail.com","1234","C://photo1.png");
        TeacherDAO teacherDAO=new TeacherDAO();
        System.out.println(teacherDAO.save(teacherToInsert));
    }
}
