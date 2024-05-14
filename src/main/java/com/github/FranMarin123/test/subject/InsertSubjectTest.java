package com.github.FranMarin123.test.subject;

import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;

public class InsertSubjectTest {
    public static void main(String[] args) {
        Teacher teacher= TeacherDAO.build().findByX("Antonio", UserField.NAME);
        Subject subjectToAdd=new Subject("Matematicas",10,teacher,null,null);
        System.out.println(SubjectDAO.build().save(subjectToAdd));
    }
}
