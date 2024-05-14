package com.github.FranMarin123.test.subject;

import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.entity.Subject;

public class DeleteSubjectTest {
    public static void main(String[] args) {
        Subject subjectToDelete=new Subject("Matematicas",0,null,null,null);
        SubjectDAO subjectDAO=new SubjectDAO();
        System.out.println(subjectDAO.delete(subjectToDelete));
    }
}
