package com.github.FranMarin123.model.singleton;

import com.github.FranMarin123.model.entity.Student;

public class StudentSession {
    private static StudentSession _instance;
    private Student currentStudent;

    private StudentSession(Student user) {
        currentStudent = user;
    }

    public static StudentSession getInstance() {
        return _instance;
    }

    public static void getInstance(Student studentToUse) {
        if (studentToUse!=null) {
            _instance = new StudentSession(studentToUse);
        }
    }

    public static void closeSession(){
        _instance=null;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }
}
