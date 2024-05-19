package com.github.FranMarin123.test.teacher;

import com.github.FranMarin123.model.dao.TeacherDAO;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;

public class TeacherSubjectTest {
    public static void main(String[] args) {
        Teacher teacher= TeacherDAO.build().findByX("Antonio", UserField.NAME);

    }
}
