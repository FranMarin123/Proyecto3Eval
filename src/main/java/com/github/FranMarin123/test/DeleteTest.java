package com.github.FranMarin123.test;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;

public class DeleteTest {
    public static void main(String[] args) {
        /*
        Student toRemove=new Student("232342d","","","","",null,null);
        StudentDAO studentDAO=new StudentDAO();
        System.out.println(studentDAO.delete(toRemove));
        */
        /*Teacher toRemove=new Teacher("","232342d","","","");
        TeacherDAO teacherDAO=new TeacherDAO();
        System.out.println(teacherDAO.delete(toRemove));
*/TeacherDAO teacherDAO=new TeacherDAO();
        SubjectDAO subjectDAO=new SubjectDAO();
        Subject sToRemove=new Subject("Matematicas",15,teacherDAO.findByX("Juanse", UserField.NAME),null,null);
        System.out.println(subjectDAO.delete(sToRemove));


    }
}
