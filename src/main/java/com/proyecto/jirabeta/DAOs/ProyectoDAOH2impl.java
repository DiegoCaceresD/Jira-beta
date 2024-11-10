package com.proyecto.jirabeta.DAOs;

import com.proyecto.jirabeta.DAOs.interfaces.EmpleadoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.ProyectoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.TareaDAO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.exceptions.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProyectoDAOH2impl implements ProyectoDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoDAOH2impl.class);

    public ProyectoDAOH2impl() {
    }

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
                throw new RollbackException("Error al rollbackear", e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
            }
        }
    }
    @Override
    public List<Proyecto> listarProyectos() throws DAOException, EntityNotFoundExcepcion {
        List<Proyecto> proyectoList = new ArrayList<>();
        String sql = "SELECT * FROM proyectos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setId(rs.getInt("id"));
                proyecto.setNombre(rs.getString("nombre"));
                proyectoList.add(proyecto);
            }
            if (proyectoList.isEmpty()){
                throw new EntityNotFoundExcepcion("No hay proyectos cargados");
            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                throw new RollbackException("Error al rollbackear", e1);
            }
            throw new DAOException("Error al crear el empleado", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
            }
        }
        return proyectoList;
    }

    @Override
    public Proyecto obtenerProyectoById(Integer id) throws DAOException, EntityNotFoundExcepcion {
        String sql = "SELECT * FROM proyectos WHERE id = '" + id + "'";
        Connection c = DBManager.connect();
        Proyecto proyecto = new Proyecto();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                proyecto.setId(rs.getInt("id"));
                proyecto.setNombre(rs.getString("nombre"));
            } else {
                throw new EntityNotFoundExcepcion("proyecto", id);
            }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RollbackException("Error al rollbackear", ex);
            }
            throw new DAOException("Error al crear el empleado", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
            }
        }
        return proyecto;
    }

    @Override
    public Proyecto obtenerProyectoByNombre(String nombre) throws DAOException, EntityNotFoundExcepcion {
        String sql = "SELECT * FROM proyectos WHERE nombre = '" + nombre + "'";
        Connection c = DBManager.connect();
        Proyecto proyecto = new Proyecto();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                proyecto.setId(rs.getInt("id"));
                proyecto.setNombre(rs.getString("nombre"));
            } else {
                throw new EntityNotFoundExcepcion("proyecto", nombre);
            }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RollbackException("Error al rollbackear", ex);
            }
            throw new DAOException("Error al crear el empleado", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
            }
        }
        return proyecto;
    }
}
