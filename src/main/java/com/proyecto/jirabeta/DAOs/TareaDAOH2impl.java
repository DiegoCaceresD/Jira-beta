package com.proyecto.jirabeta.DAOs;

import com.proyecto.jirabeta.DAOs.interfaces.EmpleadoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.ProyectoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.TareaDAO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.enums.eEstimacion;
import com.proyecto.jirabeta.enums.ePrioridad;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.exceptions.RollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDAOH2impl implements TareaDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProyectoDAOH2impl.class);
    @Override
    public void crearTarea(Tarea tarea) throws DAOException, DuplicateKeyException, EntityNotFoundExcepcion {
        float horasEstimadas = tarea.getHorasEstimadas();
        String titulo = tarea.getTitulo();
        String descripcion = tarea.getDescripcion();
        String estimacion = String.valueOf(tarea.getEstimacion());
        String prioridad = String.valueOf(tarea.getPrioridad());
        Integer responsable = null;
        Integer proyecto;
        if (tarea.getResponsable() != null) {
            responsable = tarea.getResponsable().getId();
        }
        if (tarea.getProyecto() == null ){
            throw new  EntityNotFoundExcepcion ("La tarea debe tener un proyecto asignado");
        }else {
            proyecto = tarea.getProyecto().getId();
        }

        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO tareas (horasEstimadas, titulo, descripcion, estimacion, prioridad, responsable, proyecto) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setFloat(1, horasEstimadas);
            ps.setString(2, titulo);
            ps.setString(3, descripcion);
            ps.setString(4, estimacion);
            ps.setString(5, prioridad);
            ps.setInt(6, responsable);
            ps.setInt(7, proyecto);

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
    public void actualizarTareaEnDB(Tarea tarea) throws DAOException {
        float horasEstimadas = tarea.getHorasEstimadas();
        String titulo = tarea.getTitulo();
        String descripcion = tarea.getDescripcion();
        String estimacion = String.valueOf(tarea.getEstimacion());
        String prioridad = String.valueOf(tarea.getPrioridad());
        Integer responsable = tarea.getResponsable().getId();;
        Integer proyecto = tarea.getProyecto().getId();

        String sql = "UPDATE tareas SET titulo = ?, horasestimadas = ?, descripcion = ?, estimacion = ?, prioridad = ?, responsable = ?, proyecto = ?  WHERE titulo = ?";
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setFloat(2, horasEstimadas);
            ps.setString(3, descripcion);
            ps.setString(4, estimacion);
            ps.setString(5, prioridad);
            ps.setInt(6, responsable);
            ps.setInt(7, proyecto);

            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                throw new RollbackException("Error al rollbackear", e1);
            }
            e.printStackTrace();
            throw new DAOException("Error al actualizar la tarea", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
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
                throw new RollbackException("Error al rollbackear", e1);
            }
            throw new DAOException("Error al asignar el responsable", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
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
                throw new RollbackException("Error al rollbackear", ex);
            }
            throw new DAOException("Error al eliminar la tarea", e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                logger.error("Error al cerrar la conexion con la base de datos");
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
                empleado = empleadoDAO.obtenerEmpleadoById(rs.getInt("responsable")); //todo desacoplar
                tarea.setResponsable(empleado);
            } else {
                throw new EntityNotFoundExcepcion("tarea", titulo);
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
        return tarea;
    }

    @Override
    public List<Tarea> listarTareasByIdProyecto(Integer idProyecto) throws DAOException, EntityNotFoundExcepcion {
        String sql = "SELECT * FROM tareas WHERE proyecto = ?";
        List<Tarea> tareaList = new ArrayList<>();
        Connection c = DBManager.connect();
        ProyectoDAO proyectoDAO = new ProyectoDAOH2impl();
        Proyecto proyecto;
        EmpleadoDAO empleadoDAO = new EmpleadoDAOH2impl();
        Empleado empleado;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idProyecto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setId(rs.getInt("id"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setHorasEstimadas(rs.getInt("horasEstimadas"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstimacion(eEstimacion.valueOf(rs.getString("estimacion")));
                tarea.setPrioridad(ePrioridad.valueOf(rs.getString("prioridad")));
                //obtengo el empleado completo por el Id para mapearlo a la tarea
                empleado = empleadoDAO.obtenerEmpleadoById(rs.getInt("responsable"));//todo desacoplar
                tarea.setResponsable(empleado);
                //hago lo mismo con el proyecto
                proyecto = proyectoDAO.obtenerProyectoById(idProyecto);
                tarea.setProyecto(proyecto);
                tareaList.add(tarea);
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

        return tareaList;
    }
}
