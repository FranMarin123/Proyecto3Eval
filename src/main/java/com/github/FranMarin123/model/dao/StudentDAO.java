package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.enums.UserField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO implements DAO<Student, String, UserField> {
    private final static String INSERT = "INSERT INTO student (dni,name,mail,pass,image) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE student SET REPLACE=? WHERE id_student=?";
    private final static String FINDBYX = "SELECT s.id_student,s.dni,s.name,s.mail,s.pass,s.image FROM student AS s WHERE s.REPLACE=?";
    private final static String DELETE = "DELETE FROM student WHERE dni=?";

    @Override
    public Student save(Student objectToSave) {
        Student result;
        if (objectToSave == null || objectToSave.getName() == null || objectToSave.getName().isEmpty()) {
            result = null;
        } else {
            Student studentToFind = findByX(objectToSave.getDni(), UserField.DNI);
            if (studentToFind == null) {
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, objectToSave.getDni());
                    pst.setString(2, objectToSave.getName());
                    pst.setString(3, objectToSave.getMail());
                    pst.setString(4, objectToSave.getPass());
                    pst.setString(5, objectToSave.getPhoto().toString());
                    pst.executeUpdate();
                    ResultSet rs = pst.getGeneratedKeys();
                    if (rs.first()) {
                        objectToSave.setIdUser(rs.getInt(1));
                    }
                    result = objectToSave;
                } catch (SQLException e) {
                    result = null;
                }
            } else {
                if (!studentToFind.getName().equals(objectToSave.getName()) && objectToSave.getName() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.NAME.getDbField()))) {
                        pst.setString(1, objectToSave.getName());
                        pst.setInt(2, studentToFind.getIdUser());
                        pst.executeUpdate();
                        studentToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!studentToFind.getMail().equals(objectToSave.getMail()) && objectToSave.getMail() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.MAIL.getDbField()))) {
                        pst.setString(1, objectToSave.getMail());
                        pst.setInt(2, studentToFind.getIdUser());
                        pst.executeUpdate();
                        studentToFind.setMail(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!studentToFind.getPass().equals(objectToSave.getPass()) && objectToSave.getPass() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.PASS.getDbField()))) {
                        pst.setString(1, objectToSave.getPass());
                        pst.setInt(2, studentToFind.getIdUser());
                        pst.executeUpdate();
                        studentToFind.setPass(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!studentToFind.getPhoto().toString().equals(objectToSave.getPhoto().toString()) && objectToSave.getPhoto().toString() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.PHOTO.getDbField()))) {
                        pst.setString(1, objectToSave.getPhoto().toString());
                        pst.setInt(2, studentToFind.getIdUser());
                        pst.executeUpdate();
                        studentToFind.setPass(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                result = studentToFind;
            }
        }
        return result;
    }

    @Override
    public Student delete(Student objectToDelete) {
        Student result = null;
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
    public Student findByX(String key, UserField field) {
        Student result = null;
        if (key!=null && !key.isEmpty()) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX.replaceAll("REPLACE", field.getDbField()))) {
                pst.setString(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Student();
                if (rs.first()) {
                    result.setIdUser(rs.getInt("id_student"));
                    result.setDni(rs.getString("dni"));
                    result.setName(rs.getString("name"));
                    result.setMail(rs.getString("mail"));
                    result.setPass(rs.getString("pass"));
                    result.setPhoto(rs.getString("image"));
                    result.setSubject(null);
                    result.setInscription(null);
                }
                if (result.getIdUser()<1){
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
}
