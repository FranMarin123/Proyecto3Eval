package com.github.FranMarin123.test.student;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.entity.Student;

public class InsertStudentTest {
    public static void main(String[] args) {
        StudentDAO studentDAO=new StudentDAO();
        Student studentToAdd=new Student("98765432D","Juan","juan@gmail.com","12345","C://juan.jpg",null);
        System.out.println(studentDAO.save(studentToAdd));
    }
}
