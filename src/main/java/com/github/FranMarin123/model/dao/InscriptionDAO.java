package com.github.FranMarin123.model.dao;

import com.github.FranMarin123.model.connection.ConnectionMariaDB;
import com.github.FranMarin123.model.entity.Activity;
import com.github.FranMarin123.model.entity.Inscription;
import com.github.FranMarin123.model.entity.Student;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InscriptionDAO{
    private final String INSERT="INSERT INTO inscription (id_student,id_act,nota) VALUES (?,?,?)";
    private final String ADDSCORE="UPDATE inscription set nota=? WHERE id_student=? AND id_act=?";
    private final String DELETE="DELETE FROM inscription WHERE id_student=? AND id_act=?";
    private final String DELETEFROMSTUDENT="DELETE FROM inscription WHERE id_student=?";
    private final String DELETEFROMACTIVITY="DELETE FROM inscription WHERE id_act=?";
    private final String FIND="SELECT id_student,id_act,nota FROM inscription WHERE id_act=? AND id_student=?";


    public Inscription save(Inscription inscription) {
        Inscription result=null;
        if (inscription!=null && inscription.getActivity()!=null && inscription.getStudent()!=null && find(inscription.getStudent(),inscription.getActivity())==null){
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, inscription.getStudent().getId());
                pst.setInt(2, inscription.getActivity().getId());
                pst.setInt(3, inscription.getNota());
                pst.executeUpdate();
                result=inscription;
            } catch (SQLException e) {
                result=null;
            }
        }
        return result;
    }

    public boolean addScore(Inscription inscription){
        boolean result=false;
        if (inscription!=null && inscription.getActivity()!=null && inscription.getStudent()!=null && inscription.getNota()>-1 && inscription.getNota()<11){
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(ADDSCORE, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, inscription.getNota());
                pst.setInt(2, inscription.getStudent().getId());
                pst.setInt(3, inscription.getActivity().getId());
                pst.executeUpdate();
                result=true;
            } catch (SQLException e) {
                result=false;
            }
        }
        return result;
    }

    public Inscription delete(Inscription objectToDelete) {
        Inscription result=null;
        if (objectToDelete!=null && objectToDelete.getActivity()!=null && objectToDelete.getStudent()!=null){
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, objectToDelete.getStudent().getId());
                pst.setInt(2, objectToDelete.getActivity().getId());
                pst.executeUpdate();
                result=objectToDelete;
            } catch (SQLException e) {
                result=objectToDelete;
            }
        }
        return result;
    }

    public boolean deleteFromStudent(Student objectToDelete){
        boolean result=false;
        if (objectToDelete!=null && objectToDelete.getId()>0){
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETEFROMSTUDENT)) {
                pst.setInt(1, objectToDelete.getId());
                pst.executeUpdate();
                result=true;
            } catch (SQLException e) {
                result=false;
            }
        }
        return result;
    }

    public boolean deleteFromActivity(Activity objectToDelete){
        boolean result=false;
        if (objectToDelete!=null && objectToDelete.getId()>0){
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETEFROMACTIVITY)) {
                pst.setInt(1, objectToDelete.getId());
                pst.executeUpdate();
                result=true;
            } catch (SQLException e) {
                result=false;
            }
        }
        return result;
    }

    public Inscription find(Student student, Activity activity) {
        Inscription result=null;
        if (student!=null && activity!=null && student.getId()>0 && activity.getId()>0){
            try (PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FIND)) {
                pst.setInt(1, activity.getId());
                pst.setInt(2, student.getId());
                ResultSet rs = pst.executeQuery();
                if (rs.first()) {
                    result = new Inscription();
                    result.setStudent(StudentDAO.build().findById(rs.getInt("id_student")));
                    result.setActivity(ActivityDAO.build().findById(rs.getInt("id_act")));
                    result.setNota(rs.getInt("nota"));
                }
            } catch (SQLException e) {
                result = null;
            }
        }
        return result;
    }

    public void close() throws IOException {

    }

    public static InscriptionDAO build(){
        return new InscriptionDAO();
    }
}
