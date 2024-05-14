package com.github.FranMarin123.test.subject;

import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Subject;

public class FindSubjectTest {
    public static void main(String[] args) {
        SubjectDAO subjectDAO=new SubjectDAO();
        System.out.println(subjectDAO.findByX("Matematicas","name"));
    }
}
