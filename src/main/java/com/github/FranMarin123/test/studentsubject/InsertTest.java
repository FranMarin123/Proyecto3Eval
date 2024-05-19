package com.github.FranMarin123.test.studentsubject;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.enums.UserField;

public class InsertTest {
    public static void main(String[] args) {
        Student student= StudentDAO.build().findByX("98765432B", UserField.DNI);
        Subject subject=SubjectDAO.build().findByX("Lengua","name");
        System.out.println(student);
        System.out.println(subject);
        System.out.println(SubjectDAO.build().saveStudentSubject(student,subject));
    }
}
