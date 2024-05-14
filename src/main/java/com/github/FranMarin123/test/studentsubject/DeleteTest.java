package com.github.FranMarin123.test.studentsubject;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.enums.UserField;

public class DeleteTest {
    public static void main(String[] args) {
        Student student= StudentDAO.build().findByX("Juan", UserField.NAME);
        Subject subject=SubjectDAO.build().findByX("Matematicas","name");
        System.out.println(SubjectDAO.build().removeStudentFromSubject(student,subject));
    }
}
