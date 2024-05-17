package com.github.FranMarin123.model.singleton;

import com.github.FranMarin123.model.dao.SubjectDAO;
import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Teacher;

public class TeacherSession {
    private static TeacherSession _instance;
    private Teacher currentTeacher;

    private TeacherSession(Teacher user) {
        currentTeacher = user;
    }

    public static TeacherSession getInstance() {
        return _instance;
    }

    public static void getInstance(Teacher teacherToUse) {
        if (teacherToUse!=null) {
            _instance = new TeacherSession(teacherToUse);
        }
    }

    public void refreshSubjects(){
        currentTeacher.setSubjects(SubjectDAO.build().findByTeacher(currentTeacher));
    }

    public static void closeSession(){
        _instance=null;
    }

    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }
}
