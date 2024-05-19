package com.github.FranMarin123.view;

public enum Scenes {
    BASE("view/base.fxml"),
    PRINCIPAL("view/principalScreen.fxml"),
    LOGIN("view/loginScreen.fxml"),
    REGISTER("view/registerScreen.fxml"),
    TEACHERFIRST("view/teacherLoggedFirstScreen.fxml"),
    CREATESUBJECT("view/createSubjectScreen.fxml"),
    TEACHERSELECTSUBJECT("view/selectSubjectTeacher.fxml"),
    SELECTEDSUBJECT("view/selectedSubjectScreen.fxml"),
    ADDSTUDENTSUBJECT("view/addStudentScreen.fxml"),
    REMOVESTUDENTSUBJECT("view/removeStudentScreen.fxml"),
    ADDACTIVITY("view/createActivityScreen.fxml"),
    DELETEACTIVITY("view/deleteActivity.fxml"),
    BROWSESTUDENT("view/browseStudentInsertDni.fxml"),
    SHOWSTUDENTSUBJECT("view/showStudentSubject.fxml"),
    STUDENTSCORE("view/addStudentScore.fxml"),
    DELETESUBJECT("view/deleteSubjectTeacher.fxml"),
    MODIFYTEACHER("view/modifyTeacherScreen.fxml"),
    DELETETEACHER("view/deleteTeacher.fxml"),
    STUDENTFIRST("view/studentLoggedFirstScreen.fxml"),
    STUDENTSELECTSUBJECT("view/selectSubjectStudent.fxml"),
    STUDENTSELECTACTIVITY("view/selectActivitiesStudent.fxml"),
    SHOWSTUDENTSCORE("view/showStudentScore.fxml"),
    MODIFYSTUDENT("view/modifyStudentScreen.fxml"),
    DELETESTUDENT("view/deleteStudent.fxml");

    private String path;

    Scenes(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
