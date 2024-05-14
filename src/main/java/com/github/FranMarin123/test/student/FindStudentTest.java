package com.github.FranMarin123.test.student;

import com.github.FranMarin123.model.dao.StudentDAO;
import com.github.FranMarin123.model.enums.UserField;

public class FindStudentTest {
    public static void main(String[] args) {
        System.out.println(StudentDAO.build().findByX("Juan", UserField.NAME));
    }
}
