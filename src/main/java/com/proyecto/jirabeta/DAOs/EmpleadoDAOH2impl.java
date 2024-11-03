package com.proyecto.jirabeta.DAOs;

import com.proyecto.jirabeta.DAOs.interfaces.EmpleadoDAO;
import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOH2impl implements EmpleadoDAO {

    @Override
    public void crearEmpleado(Empleado empleado) throws DuplicateKeyException {
        String nombre = empleado.getNombre();
        String apellido = empleado.getApellido();
        String dni = empleado.getDni();
        String email = empleado.getEmail();
        float capacity = empleado.getCapacity();
        boolean disponible = empleado.isDisponible();

        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO empleados (nombre, apellido, dni, email, capacity, disponible) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, dni);
            ps.setString(4, email);
            ps.setFloat(5, capacity);
            ps.setBoolean(6, disponible);

            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                if (e.getErrorCode() == 23505) {
                    throw new DuplicateKeyException("El empleado con dni " + empleado.getDni() +" ya existe");
                }
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
    public void actualizarEmpleado(Empleado empleado) throws DAOException, EntityNotFoundExcepcion {

        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, dni = ?, capacity = ?, disponible = ? WHERE dni = ?";
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getDni());
            ps.setFloat(4, empleado.getCapacity());
            ps.setBoolean(5, empleado.isDisponible());
            ps.setString(6, empleado.getDni());

           int registroAfectado = ps.executeUpdate();
           if (registroAfectado == 0){
               throw new EntityNotFoundExcepcion("Empleado", empleado.getDni());
           }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
                throw new DAOException("Error al actualizar el empleado", e);
            } catch (SQLException e1) {
                e1.printStackTrace();
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
    public void eliminarEmpleadoById(long id) throws DAOException, EntityNotFoundExcepcion {
        Connection c = DBManager.connect();
        try {
            String sql = "DELETE FROM empleados WHERE id = '" + id + "'";
            Statement s = c.createStatement();
            int registrosAfectados = s.executeUpdate(sql);
            if (registrosAfectados == 0 ){
                throw new EntityNotFoundExcepcion("empleado", id);
            };
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new DAOException();
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
    public Empleado obtenerEmpleadoByDni(String dni) throws DAOException, EntityNotFoundExcepcion {
            String sql = "SELECT * FROM empleados WHERE dni = '" + dni + "'";
            Connection c = DBManager.connect();
            Empleado empleado = new Empleado();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()){
                empleado.setId(rs.getLong("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setEmail(rs.getString("email"));
                empleado.setDni(rs.getString("dni"));
                empleado.setCapacity(rs.getFloat("capacity"));
                empleado.setDisponible(rs.getBoolean("disponible"));
            }else {
                throw new EntityNotFoundExcepcion("empleado", dni);
            }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error al buscar el empleado con DNI " + dni, e);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return empleado;
    }

    @Override
    public List<Empleado> listarEmpleados() {
        List<Empleado> empleadoList = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getLong("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setEmail(rs.getString("email"));
                empleado.setDni(rs.getString("dni"));
                empleado.setCapacity(rs.getFloat("capacity"));
                empleado.setDisponible(rs.getBoolean("disponible"));
                empleadoList.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return empleadoList;
    }
}
