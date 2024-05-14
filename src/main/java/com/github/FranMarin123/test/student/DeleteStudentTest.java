package com.github.FranMarin123.test.student;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.entity.Student;

public class DeleteStudentTest {
    public static void main(String[] args) {
        StudentDAO studentDAO=new StudentDAO();
        Student studentToRemove=new Student("98765432D","","","","",null);
        System.out.println(studentDAO.delete(studentToRemove));
    }
}
