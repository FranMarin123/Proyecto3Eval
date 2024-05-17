package com.github.FranMarin123.model.singleton;

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

    public static void closeSession(){
        _instance=null;
    }

    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }
}
