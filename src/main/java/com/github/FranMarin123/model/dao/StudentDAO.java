package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.enums.UserField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class StudentDAO implements DAO<Student, String, UserField> {
    private final static String INSERT = "INSERT INTO student (dni,name,mail,pass,image) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE student SET REPLACE=? WHERE id=?";
    private final static String FINDBYX = "SELECT s.id,s.dni,s.name,s.mail,s.pass,s.image FROM student AS s WHERE s.REPLACE=?";
    private final static String FINDBYSUBJECT = "SELECT stu.id,stu.dni,stu.name,stu.mail,stu.pass,stu.image FROM STUDENTSUBJECT AS s, student AS stu WHERE s.id_student=stu.id AND s.id_subject=?";
    private final static String DELETE = "DELETE FROM student WHERE dni=?";
    private final static String DELETESUBJECTSFORSTUDENT = "DELETE FROM STUDENTSUBJECT WHERE id_student=?";

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
                        objectToSave.setId(rs.getInt(1));
                    }
                    result = objectToSave;
                } catch (SQLException e) {
                    result = null;
                }
            } else {
                if (!studentToFind.getName().equals(objectToSave.getName()) && objectToSave.getName() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.NAME.getDbField()))) {
                        pst.setString(1, objectToSave.getName());
                        pst.setInt(2, studentToFind.getId());
                        pst.executeUpdate();
                        studentToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!studentToFind.getMail().equals(objectToSave.getMail()) && objectToSave.getMail() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.MAIL.getDbField()))) {
                        pst.setString(1, objectToSave.getMail());
                        pst.setInt(2, studentToFind.getId());
                        pst.executeUpdate();
                        studentToFind.setMail(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!studentToFind.getPass().equals(objectToSave.getPass()) && objectToSave.getPass() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.PASS.getDbField()))) {
                        pst.setString(1, objectToSave.getPass());
                        pst.setInt(2, studentToFind.getId());
                        pst.executeUpdate();
                        studentToFind.setPass(objectToSave.getMail());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!studentToFind.getPhoto().toString().equals(objectToSave.getPhoto().toString()) && objectToSave.getPhoto().toString() != null) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", UserField.PHOTO.getDbField()))) {
                        pst.setString(1, objectToSave.getPhoto().toString());
                        pst.setInt(2, studentToFind.getId());
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
                deleteSubjectStudent(result);
                InscriptionDAO.build().deleteFromStudent(result);
                pst.setString(1, objectToDelete.getDni());
                pst.executeUpdate();
            } catch (SQLException e) {
                result=null;
            }
        }
        return result;
    }

    public boolean deleteSubjectStudent(Student studentToDelete) {
        boolean result = false;
        if (studentToDelete != null && studentToDelete.getId() > 0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETESUBJECTSFORSTUDENT)) {
                pst.setInt(1, studentToDelete.getId());
                pst.executeUpdate();
                result=true;
            } catch (SQLException e) {
                result=false;
            }
        }
        return result;
    }

    @Override
    public Student findByX(String key, UserField field) {
        Student result = null;
        if (key != null && !key.isEmpty()) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX.replaceAll("REPLACE", field.getDbField()))) {
                pst.setString(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Student();
                if (rs.first()) {
                    result.setId(rs.getInt("id"));
                    result.setDni(rs.getString("dni"));
                    result.setName(rs.getString("name"));
                    result.setMail(rs.getString("mail"));
                    result.setPassWithoutHash(rs.getString("pass"));
                    result.setPhoto(rs.getString("image"));
                    result.setSubjects(SubjectDAO.build().findByStudent(result));
                    result.setInscription(null);
                }
                if (result.getId() < 1) {
                    result = null;
                }
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }

    public HashMap<String, Student> findBySubject(Subject subject) {
        HashMap<String, Student> result = new HashMap<>();
        if (subject != null && subject.getId() > 0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYSUBJECT)) {
                pst.setInt(1, subject.getId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Student tmpStudent = new Student();
                    tmpStudent.setId(rs.getInt("id"));
                    tmpStudent.setDni(rs.getString("dni"));
                    tmpStudent.setName(rs.getString("name"));
                    tmpStudent.setMail(rs.getString("mail"));
                    tmpStudent.setPassWithoutHash(rs.getString("pass"));
                    tmpStudent.setPhoto(rs.getString("image"));
                    tmpStudent.addSubject(subject);
                    tmpStudent.setInscription(null);
                    result.put(tmpStudent.getDni(), tmpStudent);
                }
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }

    @Override
    public Student findById(int key) {
        Student result = null;
        if (key > 0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX.replaceAll("REPLACE", UserField.DNI.getDbField()))) {
                pst.setInt(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Student();
                if (rs.first()) {
                    result.setId(rs.getInt("id"));
                    result.setDni(rs.getString("dni"));
                    result.setName(rs.getString("name"));
                    result.setMail(rs.getString("mail"));
                    result.setPassWithoutHash(rs.getString("pass"));
                    result.setPhoto(rs.getString("image"));
                    result.setSubjects(SubjectDAO.build().findByStudent(result));
                    result.setInscription(null);
                }
                if (result.getId() < 1) {
                    result = null;
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

    public static StudentDAO build() {
        return new StudentDAO();
    }
}
