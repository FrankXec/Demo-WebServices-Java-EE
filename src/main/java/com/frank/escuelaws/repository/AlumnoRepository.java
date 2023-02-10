package com.frank.escuelaws.repository;

import java.sql.Connection;
import com.frank.escuelaws.config.dbConfig;
import com.frank.escuelaws.models.Alumno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {

    private Connection connection;
    private String query;
    private Statement s;
    private PreparedStatement ps;
    private ResultSet rs;

    public AlumnoRepository() {
        connection = dbConfig.getConnection();
    }

    public List<Alumno> getAll() {
        List<Alumno> alumnos = null;
        query = "SELECT * FROM alumno";
        try {
            s = connection.createStatement();
            rs = s.executeQuery(query);
            alumnos = new ArrayList<>();
            while (rs.next()) {

                alumnos.add(new Alumno(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getString(6)
                ));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        return alumnos;
    }

    public Alumno getOneById(Long id) {
        Alumno alumno = null;
        query = "SELECT * FROM alumno WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                alumno = new Alumno(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getString(6)
                );
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        return alumno;
    }

    public boolean save(Alumno entity) {
        boolean status = false;
        //query = "INSERT INTO alumno (name,lastname,birthday,profession) VALUES(?,?,?,?)";
        query = "INSERT INTO alumno (name,lastname,profession) VALUES(?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getLastName());
            //ps.setDate(3, entity.getBirthday());
            ps.setInt(3, entity.getProfession());

            ps.executeUpdate();
            status = true;
        } catch (Exception ex) {
            System.out.println("Error al guardar: " + ex);
        }
        return status;
    }

    public boolean update(Long id, Alumno entity) {
        boolean status = false;
        query = "UPDATE alumno SET name= ?, lastname= ?, birthday = ?, profession= ? WHERE id = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getLastName());
            ps.setDate(3, entity.getBirthday());
            ps.setInt(4, entity.getProfession());
            //ps.setLong(5, entity.getId());
            ps.setLong(5, id);

            ps.executeUpdate();
            status = true;
        } catch (Exception ex) {
            System.out.println("Error al editar: " + ex);
        }
        return status;
    }

    public boolean delete(Long id) {
        boolean status = false;
        query = "DELETE FROM alumno WHERE id = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ps.executeUpdate();
            status = true;
        } catch (Exception ex) {
            System.out.println("Error al eliminar: " + ex);
        }
        return status;
    }
}
