package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.enums.ActivityField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ActivityDAO implements DAO<Activity,String, ActivityField>{
    private final static String INSERT = "INSERT INTO activity (description,name,percent,media_file) VALUES (?,?,?,?)";
    private final static String UPDATE = "UPDATE activity SET REPLACE=? WHERE id=?";
    private final static String FINDBYX = "SELECT a.id,a.dni,a.name,a.mail,a.pass,a.image FROM activity AS a WHERE a.REPLACE=?";
    private final static String DELETE = "DELETE FROM activity WHERE name=?";


    @Override
    public Activity save(Activity objectToSave) {
        Activity result=null;
        if (objectToSave == null || objectToSave.getName() == null || objectToSave.getName().isEmpty()) {
            result = null;
        }else {
            Activity subjectToFind=findByX(objectToSave.getName(),ActivityField.NAME);
            if (subjectToFind==null){
                try (PreparedStatement pst= ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
                    pst.setString(1,objectToSave.getDescription());
                    pst.setString(2,objectToSave.getName());
                    pst.setInt(3,objectToSave.getPercent());
                    pst.setString(4,objectToSave.getMediaFile().toString());
                    //Añadir subject
                    //Añadir inscription
                    pst.executeUpdate();
                    ResultSet rs=pst.getGeneratedKeys();
                    if (rs.first()){
                        objectToSave.setId(rs.getInt(1));
                    }
                }catch (SQLException e){
                    result=null;
                }
            }else {
                if (!(subjectToFind.getName().equals(objectToSave.getName()))){
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE",ActivityField.NAME.getDbField()))) {
                        pst.setString(1, objectToSave.getName());
                        pst.setInt(2, subjectToFind.getId());
                        pst.executeUpdate();
                        subjectToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!(subjectToFind.getDescription().equals(objectToSave.getDescription()))){
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE",ActivityField.DESCRIPTION.getDbField()))) {
                        pst.setString(1, objectToSave.getDescription());
                        pst.setInt(2, subjectToFind.getId());
                        pst.executeUpdate();
                        subjectToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!(subjectToFind.getPercent()==objectToSave.getPercent())) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", ActivityField.PERCENT.getDbField()))) {
                        pst.setInt(1, objectToSave.getPercent());
                        pst.setInt(2, subjectToFind.getId());
                        pst.executeUpdate();
                        subjectToFind.setName(objectToSave.getName());
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
    public Activity delete(Activity objectToDelete) {
        Activity result = null;
        if (objectToDelete != null && (objectToDelete.getName() != null || objectToDelete.getName().isEmpty())) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                result = findByX(objectToDelete.getName(), ActivityField.NAME);
                pst.setString(1, objectToDelete.getName());
                pst.executeUpdate();
            } catch (SQLException e) {
                result=null;
            }
        }
        return result;
    }

    @Override
    public Activity findByX(String key, ActivityField field) {
        Activity result = null;
        if (key!=null && !key.isEmpty()) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX.replaceAll("REPLACE", field.getDbField()))) {
                pst.setString(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Activity();
                if (rs.first()) {
                    result.setId(rs.getInt("id_student"));
                    result.setDescription(rs.getString("description"));
                    result.setName(rs.getString("name"));
                    result.setPercent(rs.getInt("percent"));
                    result.setMediaFile(rs.getString("media_file"));
                    result.setSubject(null);
                    result.setInscription(null);
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
    public Activity findById(int key) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
