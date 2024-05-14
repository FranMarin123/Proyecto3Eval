package com.github.FranMarin123.test.teacher;

import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.enums.UserField;

public class FindTeacherTest {
    public static void main(String[] args) {
        System.out.println(TeacherDAO.build().findByX("Antonio", UserField.NAME));
        System.out.println(TeacherDAO.build().findById(3));
    }
}
