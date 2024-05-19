package com.github.FranMarin123.test.studentsubject;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.enums.UserField;

public class DeleteTest {
    public static void main(String[] args) {
        Student student= StudentDAO.build().findByX("98765432B", UserField.DNI);
        Subject subject=SubjectDAO.build().findByX("Lengua","name");
        System.out.println(SubjectDAO.build().removeStudentFromSubject(student,subject));
    }
}
