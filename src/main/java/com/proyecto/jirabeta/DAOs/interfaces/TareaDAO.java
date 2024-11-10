package com.proyecto.jirabeta.DAOs.interfaces;

import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;

import java.util.List;

public interface TareaDAO {

    public void crearTarea(Tarea tarea) throws DAOException, DuplicateKeyException, EntityNotFoundExcepcion;
    public void actualizarTareaEnDB(Tarea tarea) throws DAOException;
    public void asignarResponsable(String tituloTarea, Integer idResponsable) throws DAOException, EntityNotFoundExcepcion;
    public void eliminarTareaById(Integer id) throws DAOException, EntityNotFoundExcepcion;
    public Tarea obtenerTareaPorTitulo(String titulo) throws DAOException, EntityNotFoundExcepcion;

    List<Tarea> listarTareasByIdProyecto(Integer idProyecto) throws DAOException, EntityNotFoundExcepcion;

}
