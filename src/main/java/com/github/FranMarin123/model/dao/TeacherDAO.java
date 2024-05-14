package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class TeacherDAO implements DAO<Teacher, String, UserField>{
    private final static String INSERT = "INSERT INTO teacher (dni,name,mail,pass,image) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE teacher SET REPLACE=? WHERE id=?";
    private final static String FINDBYX = "SELECT t.id,t.dni,t.name,t.mail,t.pass,t.image FROM teacher AS t WHERE t.REPLACE=?";
    private final static String FINDBYID = "SELECT t.id,t.dni,t.name,t.mail,t.pass,t.image FROM teacher AS t WHERE t.id=?";
    private final static String DELETE = "DELETE FROM teacher WHERE dni=?";

    @Override
    public Teacher save(Teacher objectToSave) {
        Teacher result;
        if (objectToSave == null || objectToSave.getName() == null || objectToSave.getName().isEmpty()) {
            result = null;
        } else {
            Teacher teacherToFind = findByX(objectToSave.getDni(), UserField.DNI);
            if (teacherToFind == null) {
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, objectToSave.getDni());
                    pst.setString(2, objectToSave.getName());
                    pst.setString(3, objectToSave.getMail());
                    pst.setString(4, objectToSave.getPass());
                    pst.setString(5, objectToSave.getPhoto().toString());
                    pst.executeUpdate();
                    ResultSet rs = pst.getGeneratedKeys();
                    if (rs.first()) {
                        objectToSave.setId(rs.getInt(1));
                    }
                    result = objectToSave;
                } catch (SQLException e) {
                    result = null;
                }
            } else {
                if (!teacherToFind.getName().equals(objectToSave.getName()) && objectToSave.getName() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.NAME.getDbField()))) {
                        pst.setString(1, objectToSave.getName());
                        pst.setInt(2, teacherToFind.getId());
                        pst.executeUpdate();
                        teacherToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!teacherToFind.getMail().equals(objectToSave.getMail()) && objectToSave.getMail() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.MAIL.getDbField()))) {
                        pst.setString(1, objectToSave.getMail());
                        pst.setInt(2, teacherToFind.getId());
                        pst.executeUpdate();
                        teacherToFind.setMail(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!teacherToFind.getPass().equals(objectToSave.getPass()) && objectToSave.getPass() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.PASS.getDbField()))) {
                        pst.setString(1, objectToSave.getPass());
                        pst.setInt(2, teacherToFind.getId());
                        pst.executeUpdate();
                        teacherToFind.setPass(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!teacherToFind.getPhoto().toString().equals(objectToSave.getPhoto().toString()) && objectToSave.getPhoto().toString() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.PHOTO.getDbField()))) {
                        pst.setString(1, objectToSave.getPhoto().toString());
                        pst.setInt(2, teacherToFind.getId());
                        pst.executeUpdate();
                        teacherToFind.setPass(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                result = teacherToFind;
            }
        }
        return result;
    }

    @Override
    public Teacher delete(Teacher objectToDelete) {
        Teacher result = null;
        if (objectToDelete != null && objectToDelete.getDni() != null) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                result = findByX(objectToDelete.getDni(), UserField.DNI);
                pst.setString(1, objectToDelete.getDni());
                pst.executeUpdate();
            } catch (SQLException e) {
            }
        }
        return result;
    }

    @Override
    public Teacher findByX(String key, UserField field) {
        Teacher result = null;
        if (key!=null && !key.isEmpty()) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX.replaceAll("REPLACE", field.getDbField()))) {
                pst.setString(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Teacher();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setDni(rs.getString("dni"));
                    result.setName(rs.getString("name"));
                    result.setMail(rs.getString("mail"));
                    result.setPass(rs.getString("pass"));
                    result.setPhoto(rs.getString("image"));
                    result.setSubjects(SubjectDAO.build().findByTeacher(result));
                }
                if (result.getId()<1){
                    result=null;
                }
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }

    @Override
    public Teacher findById(int key) {
        Teacher result = null;
        if (key>0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Teacher();
                if (rs.next()) {
                    result.setId(rs.getInt("id"));
                    result.setDni(rs.getString("dni"));
                    result.setName(rs.getString("name"));
                    result.setMail(rs.getString("mail"));
                    result.setPass(rs.getString("pass"));
                    result.setPhoto(rs.getString("image"));
                }
                if (result.getId()<1){
                    result=null;
                }
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }


    @Override
    public void close() throws IOException {

    }

    public static TeacherDAO build(){
        return new TeacherDAO();
    }
}
