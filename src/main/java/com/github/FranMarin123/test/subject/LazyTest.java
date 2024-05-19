package com.github.FranMarin123.test.subject;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Subject;

public class LazyTest {
    public static void main(String[] args) {
        Subject subject= SubjectDAO.build().findByX("Lengua","name");
        System.out.println(subject.getName());
        System.out.println(subject.getStudents());
    }
}
