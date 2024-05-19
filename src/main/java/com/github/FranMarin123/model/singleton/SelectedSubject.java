package com.github.FranMarin123.model.singleton;

import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Subject;

public class SelectedSubject {
    private static SelectedSubject _instance;
    private Subject currentSubject;

    private SelectedSubject(Subject subject) {
        currentSubject = subject;
    }

    public static SelectedSubject getInstance() {
        return _instance;
    }

    public static void getInstance(Subject subjectToUse) {
        if (subjectToUse!=null) {
            _instance = new SelectedSubject(subjectToUse);
        }
    }

    public static void removeSubject(){
        _instance=null;
    }

    public Subject getCurrentSubject() {
        return currentSubject;
    }
}
