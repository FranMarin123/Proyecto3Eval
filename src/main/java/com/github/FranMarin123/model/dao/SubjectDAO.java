package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.enums.UserField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SubjectDAO implements DAO<Subject, String, String> {
    private final static String INSERT = "INSERT INTO subject (name,hours,id_teacher) VALUES (?,?,?)";
    private final static String UPDATE = "UPDATE subject SET hours=? WHERE id=?";
    private final static String FINDBYX = "SELECT s.id,s.name,s.hours,s.id_teacher FROM subject AS s WHERE s.name=?";
    private final static String DELETE = "DELETE FROM subject WHERE name=?";

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
                    //Añadir profesor
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

    @Override
    public Subject delete(Subject objectToDelete) {
        Subject result = null;
        if (objectToDelete != null && (objectToDelete.getName() != null || objectToDelete.getName().isEmpty())) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                result = findByX(objectToDelete.getName(), "name");
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
        Subject result = null;
        if (key!=null && !key.isEmpty()) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX)) {
                pst.setString(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Subject();
                if (rs.first()) {
                    result.setId(rs.getInt("id"));
                    result.setName(rs.getString("name"));
                    result.setHours(rs.getInt("hours"));
                    result.setStudents(null);
                    result.setTeacher(TeacherDAO.build().findById(rs.getInt("id_teacher")));
                }
                if (result.getId()<1){
                    result=null;
                }
            } catch (SQLException e) {
                result = null;
                System.out.println("ERROR");
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
}
