package com.github.FranMarin123.test;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Teacher;

public class InsertTest {
    public static void main(String[] args) {

        StudentDAO testStudent=new StudentDAO();
        Student student=new Student("232342d","Juanse","juanse123@gmail.com","1234","C://juan.jpg",null,null);
        System.out.println(testStudent.save(student));

        TeacherDAO testTeacher=new TeacherDAO();
        Teacher teacher=new Teacher("Juanse","232342d","juanse@gmail.com","1234","C://juan.jpg");
        System.out.println(testTeacher.save(teacher));

    }
}
