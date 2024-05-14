package com.github.FranMarin123.model.entity;

import java.util.HashMap;
import java.util.Objects;

public class Subject {
    private int id;
    private String name;
    private int hours;
    private Teacher teacher;
    private HashMap<String,Activity> activities;
    private HashMap<String,Student> students;

    public Subject(String name, int hours, Teacher teacher, HashMap<String,Activity> activities,HashMap<String,Student> students) {
        this.name = name;
        this.hours = hours;
        this.teacher = teacher;
        this.activities = activities;
        this.students = students;
    }

    public Subject() {
        this("", -1, new Teacher(), new HashMap<>(),new HashMap<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public HashMap<String,Activity> getActivities() {
        return activities;
    }

    public void setActivities(HashMap<String,Activity> activities) {
        this.activities = activities;
    }

    public HashMap<String,Student> getStudents() {
        return students;
    }

    public void setStudents(HashMap<String,Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Subject subject = (Subject) object;
        return id == subject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", teacher=" + teacher +
                ", activities=" + activities +
                ", students=" + students +
                '}';
    }

    public boolean addActivity(Activity activityToAdd){
        boolean result=false;
        if(activityToAdd!=null && activityToAdd.getName()!=null && !activityToAdd.getName().isEmpty()) {
            if (activities == null) {
                activities = new HashMap<>();
            }
            if (activities.containsKey(activityToAdd.getName())) {
                activities.put(activityToAdd.getName(), activityToAdd);
                result = true;
            }
        }
        return result;
    }

    public boolean addStudent(Student studentToAdd){
        boolean result=false;
        if(studentToAdd!=null && studentToAdd.getDni()!=null && !studentToAdd.getDni().isEmpty()) {
            if (students == null) {
                students = new HashMap<>();
            }
            if (students.containsKey(studentToAdd.getDni())) {
                students.put(studentToAdd.getDni(), studentToAdd);
                result = true;
            }
        }
        return result;
    }

    public boolean removeActivity(Activity actToRemove){
        boolean result=false;
        if (actToRemove!=null && actToRemove.getName()!=null && !actToRemove.getName().isEmpty()){
            if (activities.containsKey(actToRemove.getName())){
                activities.remove(actToRemove.getName());
                result=true;
            }
        }
        return result;
    }

    public boolean removeStudent(Student studentToRemove){
        boolean result=false;
        if (studentToRemove!=null && studentToRemove.getDni()!=null && !studentToRemove.getDni().isEmpty()){
            if (students.containsKey(studentToRemove.getDni())){
                students.remove(studentToRemove.getDni());
                result=true;
            }
        }
        return result;
    }
}
