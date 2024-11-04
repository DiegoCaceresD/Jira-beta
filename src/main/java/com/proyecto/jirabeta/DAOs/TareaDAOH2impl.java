package com.proyecto.jirabeta.DAOs;

import com.proyecto.jirabeta.DAOs.interfaces.EmpleadoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.TareaDAO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.enums.eEstimacion;
import com.proyecto.jirabeta.enums.ePrioridad;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;

import java.sql.*;

public class TareaDAOH2impl implements TareaDAO {
    @Override
    public void crearTarea(Tarea tarea) throws DAOException, DuplicateKeyException {
        float horasEstimadas = tarea.getHorasEstimadas();
        String titulo = tarea.getTitulo();
        String descripcion = tarea.getDescripcion();
        String estimacion = String.valueOf(tarea.getEstimacion());
        String prioridad = String.valueOf(tarea.getPrioridad());
        Integer responsable;
        //es una fk por lo que no puede ser nula
        if (tarea.getResponsable() == null) {
            throw new IllegalArgumentException("La tarea debe tener un responsable asignado");
        } else {
            responsable = tarea.getResponsable().getId();
        }
//        long proyecto = tarea.getProyecto().getId();
//manejar los nulls
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO tareas (horasEstimadas, titulo, descripcion, estimacion, prioridad, responsable) VALUES (?, ?, ?, ?, ?, ?)"); //todo recordar agregar proyecto cuando se desarrolle esa parte
            ps.setFloat(1, horasEstimadas);
            ps.setString(2, titulo);
            ps.setString(3, descripcion);
            ps.setString(4, estimacion);
            ps.setString(5, prioridad);
            ps.setInt(6, responsable);
//            ps.setLong(7, proyecto);

            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                if (e.getErrorCode() == 23505) {
                    throw new DuplicateKeyException("La tarea con titulo: " + titulo + " ya existe");
                }
                throw new DAOException("Error al crear la tarea", e);
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
    public void actualizarTareaEnDB(Tarea tarea) throws DAOException, EntityNotFoundExcepcion {
        float horasEstimadas = tarea.getHorasEstimadas();
        String titulo = tarea.getTitulo();
        String descripcion = tarea.getDescripcion();
        String estimacion = String.valueOf(tarea.getEstimacion());
        String prioridad = String.valueOf(tarea.getPrioridad());
        Integer responsable = tarea.getResponsable().getId();;

        //        long proyecto = tarea.getProyecto().getId();

        String sql = "UPDATE tareas SET titulo = ?, horasestimadas = ?, descripcion = ?, estimacion = ?, prioridad = ?, responsable = ?  WHERE titulo = ?";
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setFloat(2, horasEstimadas);
            ps.setString(3, descripcion);
            ps.setString(4, estimacion);
            ps.setString(5, prioridad);
            ps.setInt(6, responsable);
            ps.setString(7, titulo);

            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            throw new DAOException("Error al actualizar la tarea", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void asignarResponsable(String tituloTarea, Integer idResponsable) throws DAOException, EntityNotFoundExcepcion {
        String sql = "UPDATE tareas SET responsable = ?  WHERE titulo = ?";
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idResponsable);
            ps.setString(2, tituloTarea);
            int registroAfectado = ps.executeUpdate();
            if (registroAfectado == 0) {
                throw new EntityNotFoundExcepcion("Tarea", tituloTarea);
            }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DAOException("Error al asignar el responsable", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void eliminarTareaById(Integer id) throws DAOException, EntityNotFoundExcepcion {
        Connection c = DBManager.connect();
        try {
            String sql = "DELETE FROM tareas WHERE id = '" + id + "'";
            Statement s = c.createStatement();
            int registrosAfectados = s.executeUpdate(sql);
            if (registrosAfectados == 0) {
                throw new EntityNotFoundExcepcion("tarea", id);
            }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DAOException("Error al eliminar la tarea", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public Tarea obtenerTareaPorTitulo(String titulo) throws DAOException, EntityNotFoundExcepcion {
        String sql = "SELECT * FROM tareas WHERE titulo = '" + titulo + "'";
        Connection c = DBManager.connect();
        Tarea tarea = new Tarea();
        EmpleadoDAO empleadoDAO = new EmpleadoDAOH2impl();
        Empleado empleado;
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                tarea.setId(rs.getInt("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setHorasEstimadas(rs.getInt("horasEstimadas"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstimacion(eEstimacion.valueOf(rs.getString("estimacion")));
                tarea.setPrioridad(ePrioridad.valueOf(rs.getString("prioridad")));
                //obtengo el empleado completo por el Id para mapearlo a la tarea
                empleado = empleadoDAO.obtenerEmpleadoById(rs.getInt("responsable"));
                tarea.setResponsable(empleado);
            } else {
                throw new EntityNotFoundExcepcion("tarea", titulo);
            }
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DAOException("Error al crear el empleado", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return tarea;
    }
}
