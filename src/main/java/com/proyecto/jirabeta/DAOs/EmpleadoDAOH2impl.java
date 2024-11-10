package com.proyecto.jirabeta.DAOs;

import com.proyecto.jirabeta.DAOs.interfaces.EmpleadoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.ProyectoDAO;
import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.exceptions.RollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoDAOH2impl implements EmpleadoDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProyectoDAOH2impl.class);

    @Override
    public void crearEmpleado(Empleado empleado) throws DAOException, DuplicateKeyException {
        String nombre = empleado.getNombre();
        String apellido = empleado.getApellido();
        String dni = empleado.getDni();
        String email = empleado.getEmail();
        float capacity = empleado.getCapacity();
        boolean disponible = empleado.isDisponible();
        Integer proyecto;
        if (empleado.getProyecto() == null) {
            throw new IllegalArgumentException("El empleado debe pertenecer a un proyecto");
        } else {
            proyecto = empleado.getProyecto().getId();
        }

        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO empleados (nombre, apellido, dni, email, capacity, disponible, proyecto) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, dni);
            ps.setString(4, email);
            ps.setFloat(5, capacity);
            ps.setBoolean(6, disponible);
            ps.setInt(7, proyecto);

            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                if (e.getErrorCode() == 23505) {
                    throw new DuplicateKeyException("El empleado con dni " + empleado.getDni() + " ya existe");
                }
                throw new DAOException("Error al crear el empleado", e);
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
    public void actualizarEmpleado(Empleado empleado) throws DAOException{

        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, dni = ?, capacity = ?, disponible = ?, proyecto = ? WHERE dni = ?";
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getDni());
            ps.setFloat(4, empleado.getCapacity());
            ps.setBoolean(5, empleado.isDisponible());
            ps.setString(6, empleado.getDni());
            ps.setInt(7, empleado.getProyecto().getId());

            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                throw new RollbackException("Error al rollbackear", e1);
            }
            throw new DAOException("Error al actualizar el empleado", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
            }
        }
    }

    @Override
    public void eliminarEmpleadoById(Integer id) throws DAOException, EntityNotFoundExcepcion {
        Connection c = DBManager.connect();
        try {
            String sql = "DELETE FROM empleados WHERE id = '" + id + "'";
            Statement s = c.createStatement();
            int registrosAfectados = s.executeUpdate(sql);
            if (registrosAfectados == 0) {
                throw new EntityNotFoundExcepcion("empleado", id);
            }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RollbackException("Error al rollbackear", ex);
            }
            throw new DAOException("Error al eliminar el empleado", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
            }
        }
    }

    @Override
    public Empleado obtenerEmpleadoByDni(String dni) throws DAOException, EntityNotFoundExcepcion {
        String sql = "SELECT * FROM empleados WHERE dni = '" + dni + "'";
        Connection c = DBManager.connect();
        Empleado empleado = new Empleado();
        ProyectoDAO proyectoDAO = new ProyectoDAOH2impl();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setEmail(rs.getString("email"));
                empleado.setDni(rs.getString("dni"));
                empleado.setCapacity(rs.getFloat("capacity"));
                empleado.setDisponible(rs.getBoolean("disponible"));
                Proyecto proyecto = proyectoDAO.obtenerProyectoById(rs.getInt("proyecto"));//todo desacoplar
                empleado.setProyecto(proyecto);
            } else {
                throw new EntityNotFoundExcepcion("empleado", dni);
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
        return empleado;
    }

    @Override
    public Empleado obtenerEmpleadoById(Integer id) throws DAOException, EntityNotFoundExcepcion {
        String sql = "SELECT * FROM empleados WHERE id = '" + id + "'";
        Connection c = DBManager.connect();
        Empleado empleado = new Empleado();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setEmail(rs.getString("email"));
                empleado.setDni(rs.getString("dni"));
                empleado.setCapacity(rs.getFloat("capacity"));
                empleado.setDisponible(rs.getBoolean("disponible"));
            } else {
                throw new EntityNotFoundExcepcion("empleado", id);
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
        return empleado;
    }

    @Override
    public List<Empleado> listarEmpleados() throws DAOException, EntityNotFoundExcepcion {
        List<Empleado> empleadoList = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setEmail(rs.getString("email"));
                empleado.setDni(rs.getString("dni"));
                empleado.setCapacity(rs.getFloat("capacity"));
                empleado.setDisponible(rs.getBoolean("disponible"));
                empleadoList.add(empleado);
            }
            if (empleadoList.isEmpty()) {
                throw new EntityNotFoundExcepcion("No hay empleados cargados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return empleadoList;
    }

    public List<Empleado> listarEmpleadosByIdProyecto(Integer idProyecto) throws DAOException, EntityNotFoundExcepcion {
        String sql = "SELECT * FROM empleados WHERE proyecto = ?";
        List<Empleado> empleados = new ArrayList<>();
        Connection c = DBManager.connect();

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setEmail(rs.getString("email"));
                empleado.setDni(rs.getString("dni"));
                empleado.setCapacity(rs.getFloat("capacity"));
                empleado.setDisponible(rs.getBoolean("disponible"));
                empleados.add(empleado);
            }

            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RollbackException("Error al rollbackear", ex);
            }
            throw new DAOException("Error al obtener empleados por ID de proyecto", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
            }
        }

        return empleados;
    }
}
