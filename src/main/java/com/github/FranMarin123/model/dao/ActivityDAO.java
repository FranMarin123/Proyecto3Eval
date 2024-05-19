package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.entity.Inscription;
import com.github.FranMarin123.model.entity.Subject;
import com.github.FranMarin123.model.enums.ActivityField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class ActivityDAO implements DAO<Activity, String, ActivityField> {
    private final static String INSERT = "INSERT INTO activity (description,name,percent,media_file,id_subject) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE activity SET REPLACE=? WHERE id=?";
    private final static String FINDBYX = "SELECT a.id,a.description,a.name,a.percent,a.media_file,a.id_subject FROM activity AS a WHERE a.REPLACE=?";
    private final static String FINDBYID = "SELECT a.id,a.description,a.name,a.percent,a.media_file,a.id_subject FROM activity AS a WHERE a.id=?";
    private final static String FINDBYSUBJECT = "SELECT a.id,a.description,a.name,a.percent,a.media_file,a.id_subject FROM activity AS a WHERE a.id_subject=?";
    private final static String DELETE = "DELETE FROM activity WHERE name=?";


    @Override
    public Activity save(Activity objectToSave) {
        Activity result = null;
        if (objectToSave == null || objectToSave.getName() == null || objectToSave.getName().isEmpty()) {
            result = null;
        } else {
            Activity subjectToFind = findByX(objectToSave.getName(), ActivityField.NAME);
            if (subjectToFind == null) {
                try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, objectToSave.getDescription());
                    pst.setString(2, objectToSave.getName());
                    pst.setInt(3, objectToSave.getPercent());
                    pst.setString(4, objectToSave.getMediaFile().toString());
                    if (objectToSave.getSubject() != null && objectToSave.getSubject().getId() > 0) {
                        pst.setInt(5, objectToSave.getSubject().getId());
                    }
                    //Añadir inscription
                    pst.executeUpdate();
                    ResultSet rs = pst.getGeneratedKeys();
                    if (rs.first()) {
                        objectToSave.setId(rs.getInt(1));
                    }
                    result=objectToSave;
                } catch (SQLException e) {
                    result = null;
                }
            } else {
                if (!(subjectToFind.getName().equals(objectToSave.getName()))) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", ActivityField.NAME.getDbField()))) {
                        pst.setString(1, objectToSave.getName());
                        pst.setInt(2, subjectToFind.getId());
                        pst.executeUpdate();
                        subjectToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!(subjectToFind.getDescription().equals(objectToSave.getDescription()))) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", ActivityField.DESCRIPTION.getDbField()))) {
                        pst.setString(1, objectToSave.getDescription());
                        pst.setInt(2, subjectToFind.getId());
                        pst.executeUpdate();
                        subjectToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }
                if (!(subjectToFind.getPercent() == objectToSave.getPercent())) {
                    try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE.replaceAll("REPLACE", ActivityField.PERCENT.getDbField()))) {
                        pst.setInt(1, objectToSave.getPercent());
                        pst.setInt(2, subjectToFind.getId());
                        pst.executeUpdate();
                        subjectToFind.setName(objectToSave.getName());
                    } catch (SQLException e) {
                        result = null;
                    }
                }

                result = subjectToFind;
            }
        }
        return result;
    }

    @Override
    public Activity delete(Activity objectToDelete) {
        Activity result = null;
        if (objectToDelete != null && (objectToDelete.getName() != null || objectToDelete.getName().isEmpty())) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                InscriptionDAO.build().deleteFromActivity(objectToDelete);
                pst.setString(1, objectToDelete.getName());
                pst.executeUpdate();
                result = objectToDelete;
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }

    @Override
    public Activity findByX(String key, ActivityField field) {
        Activity result = null;
        if (key != null && !key.isEmpty()) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYX.replaceAll("REPLACE", field.getDbField()))) {
                pst.setString(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Activity();
                if (rs.first()) {
                    result.setId(rs.getInt("id"));
                    result.setDescription(rs.getString("description"));
                    result.setName(rs.getString("name"));
                    result.setPercent(rs.getInt("percent"));
                    result.setMediaFile(rs.getString("media_file"));
                    result.setSubject(SubjectDAO.build().findById(rs.getInt("id_subject")));
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
    public Activity findById(int key) {
        Activity result = null;
        if (key > 0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, key);
                ResultSet rs = pst.executeQuery();
                result = new Activity();
                if (rs.first()) {
                    result.setId(rs.getInt("id"));
                    result.setDescription(rs.getString("description"));
                    result.setName(rs.getString("name"));
                    result.setPercent(rs.getInt("percent"));
                    result.setMediaFile(rs.getString("media_file"));
                    result.setSubject(SubjectDAO.build().findById(rs.getInt("id_subject")));
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

    public HashMap<String, Activity> findBySubject(Subject subject) {
        HashMap<String, Activity> result = new HashMap<>();
        if (subject != null && subject.getId() > 0) {
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYSUBJECT)) {
                pst.setInt(1, subject.getId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Activity tmpActivity=new Activity();
                    tmpActivity.setId(rs.getInt("id"));
                    tmpActivity.setDescription(rs.getString("description"));
                    tmpActivity.setName(rs.getString("name"));
                    tmpActivity.setPercent(rs.getInt("percent"));
                    tmpActivity.setMediaFile(rs.getString("media_file"));
                    tmpActivity.setSubject(SubjectDAO.build().findById(rs.getInt("id_subject")));
                    tmpActivity.setInscription(null);
                    result.put(tmpActivity.getName(), tmpActivity);
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

    public static ActivityDAO build() {
        return new ActivityDAO();
    }
}
