package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.entity.Student;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.entity.Teacher;
import com.github.FranMarin123.model.enums.UserField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubjectDAO implements DAO<Subject, String, String> {
    private final static String INSERT = "INSERT INTO subject (name,hours,id_teacher) VALUES (?,?,?)";
    private final static String INSERTSTUSUB = "INSERT INTO studentsubject (id_subject,id_student) VALUES (?,?)";
    private final static String SELECTSTUSUB = "SELECT id_subject,id_student FROM studentsubject WHERE id_subject=? AND id_student=?";
    private final static String UPDATE = "UPDATE subject SET hours=? WHERE id=?";
    private final static String FINDBYX = "SELECT s.id,s.name,s.hours,s.id_teacher FROM subject AS s WHERE s.name=?";
    private final static String FINDBYTEACHER = "SELECT s.id,s.name,s.hours,s.id_teacher FROM subject AS s WHERE s.id_teacher=?";
    private final static String FINDBYSTUDENT = "SELECT su.id,su.name,su.hours,su.id_teacher FROM subject AS su, studentsubject AS st WHERE su.id=st.id_subject AND st.id_student=?";
    private final static String DELETE = "DELETE FROM subject WHERE name=?";
    private final static String DELETEALLSTUDENTS = "DELETE FROM studentsubject WHERE id_subject=?";
    private final static String DELETESTUSUB = "DELETE FROM studentsubject WHERE id_subject=? AND id_student=?";

    @Override
    public Subject save(Subject objectToSave) {
        Subject result=null;
        if (objectToSave == null || objectToSave.getName() == null || objectToSave.getName().isEmpty()) {
            result = null;
        }else {
            Subject subjectToFind=findByX(objectToSave.getName(),"name");
            if (subjectToFind==null){
                try (PreparedStatement pst= ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
                    pst.setString(1,objectToSave.getName());
                    pst.setInt(2,objectToSave.getHours());
                    pst.setInt(3,objectToSave.getTeacher().getId());
                    pst.executeUpdate();
                    ResultSet rs=pst.getGeneratedKeys();
                    if (rs.first()){
                        objectToSave.setId(rs.getInt(1));
                    }
                    result=objectToSave;
                }catch (SQLException e){
                    result=null;
                }
            }else {
                if (!(subjectToFind.getHours()==objectToSave.getHours())){
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                        pst.setInt(1, objectToSave.getHours());
                        pst.setInt(2, subjectToFind.getId());
                        pst.executeUpdate();
                        subjectToFind.setHours(objectToSave.getHours());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                result=subjectToFind;
            }
        }
        return result;
    }

    public boolean saveStudentSubject(Student student,Subject subject){
        boolean result=false;
        if (student!=null && subject!=null && student.getId()>0 && subject.getId()>0 && !proveStudentSubject(student.getId(), subject.getId())){
            try (PreparedStatement pst= ConnectionMariaDB.getConnection().prepareStatement(INSERTSTUSUB, Statement.RETURN_GENERATED_KEYS)){
                pst.setInt(1,subject.getId());
                pst.setInt(2,student.getId());
                pst.executeUpdate();
                result=true;
            }catch (SQLException e){
                result=false;
            }

        }
        return result;
    }

    public boolean proveStudentSubject(int idStudent, int idSubject){
        boolean result=false;
        if (idStudent>0 && idSubject>0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(SELECTSTUSUB)) {
                pst.setInt(1, idSubject);
                pst.setInt(2, idStudent);
                ResultSet rs = pst.executeQuery();
                if (rs.first()) {
                    if (rs.getInt("id_subject")>0 || rs.getInt("id_student")>0){
                        result=true;
                    }
                }
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }

    public boolean removeStudentFromSubject(Student student,Subject subject){
        boolean result = false;
        if (student!=null && subject!=null && student.getId()>0 && subject.getId()>0 && proveStudentSubject(student.getId(), subject.getId())) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETESTUSUB)) {
                pst.setInt(1, subject.getId());
                pst.setInt(2, student.getId());
                pst.executeUpdate();
                result=true;
            } catch (SQLException e) {
                result=false;
            }
        }
        return result;
    }

    @Override
    public Subject delete(Subject objectToDelete) {
        Subject result = null;
        if (objectToDelete != null && (objectToDelete.getName() != null || objectToDelete.getName().isEmpty())) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETEALLSTUDENTS)) {
                result = findByX(objectToDelete.getName(), "name");
                pst.setInt(1, objectToDelete.getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                result=null;
            }
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setString(1, objectToDelete.getName());
                pst.executeUpdate();
            } catch (SQLException e) {
                result=null;
            }
        }
        return result;
    }

    @Override
    public Subject findByX(String key, String field) {
        Subject result = new SubjectLazy();
        if (key!=null && !key.isEmpty()) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX)) {
                pst.setString(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Subject();
                if (rs.first()) {
                    result.setId(rs.getInt("id"));
                    result.setName(rs.getString("name"));
                    result.setHours(rs.getInt("hours"));
                    result.setTeacher(TeacherDAO.build().findById(rs.getInt("id_teacher")));
                    result.setStudents(StudentDAO.build().findBySubject(result));
                    result.setActivities(ActivityDAO.build().findBySubject(result));
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

    public Set<Subject> findByTeacher(Teacher teacher) {
        Set<Subject> result = new HashSet<Subject>();
        if (teacher!=null && teacher.getId()>0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYTEACHER)) {
                pst.setInt(1, teacher.getId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Subject tmpSubject=new SubjectLazy();
                    tmpSubject.setId(rs.getInt("id"));
                    tmpSubject.setName(rs.getString("name"));
                    tmpSubject.setHours(rs.getInt("hours"));
                    tmpSubject.setTeacher(TeacherDAO.build().findById(rs.getInt("id_teacher")));
                    result.add(tmpSubject);
                }
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }

    public Set<Subject> findByStudent(Student student) {
        Set<Subject> result = new HashSet<Subject>();
        if (student!=null && student.getId()>0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYSTUDENT)) {
                pst.setInt(1, student.getId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Subject tmpSubject=new SubjectLazy();
                    tmpSubject.setId(rs.getInt("id"));
                    tmpSubject.setName(rs.getString("name"));
                    tmpSubject.setHours(rs.getInt("hours"));
                    tmpSubject.setTeacher(TeacherDAO.build().findById(rs.getInt("id_teacher")));
                    result.add(tmpSubject);
                }
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }

    @Override
    public Subject findById(int key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }

    public static SubjectDAO build(){
        return new SubjectDAO();
    }
}

class SubjectLazy extends Subject{

    @Override
    public HashMap<String, Student> getStudents(){
        if (super.getStudents()==null){
            System.out.println("Bien");
            //setStudents(StudentDAO.build().findBySubject(this));
        }
        return super.getStudents();
    }

    /*public HashMap<String, Activity> getActivities(){
        if (super.getActivities()==null){
            super.setActivities(ActivityDAO.build().findBySubject(this));
        }
        return super.getActivities();
    }*/
}
