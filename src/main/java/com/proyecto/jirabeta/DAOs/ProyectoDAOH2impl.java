package com.proyecto.jirabeta.DAOs;

import com.proyecto.jirabeta.DAOs.interfaces.ProyectoDAO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProyectoDAOH2impl implements ProyectoDAO {
    @Override
    public void crearProyecto(Proyecto proyecto) throws DAOException, DuplicateKeyException {
        String nombre = proyecto.getNombre();
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO proyectos (nombre) VALUES (?)");
            ps.setString(1, nombre);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                if (e.getErrorCode() == 23505) {
                    throw new DuplicateKeyException("El proyecto: " + nombre + " ya existe");
                }
                throw new DAOException("Error al crear el proyecto", e);
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void asignarTarea(Tarea tarea) throws DAOException, EntityNotFoundExcepcion {

    }

    @Override
    public List<Proyecto> listarProyectos() throws DAOException {
        return null;
    }

    @Override
    public Proyecto obtenerProyectoById(Integer id) throws DAOException, EntityNotFoundExcepcion {
        return null;
    }

    @Override
    public Proyecto obtenerProyectoByNombre(String nombre) throws DAOException, EntityNotFoundExcepcion {
        return null;
    }
}
