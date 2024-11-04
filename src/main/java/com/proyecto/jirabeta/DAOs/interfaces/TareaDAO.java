package com.proyecto.jirabeta.DAOs.interfaces;

import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;

public interface TareaDAO {

    public void crearTarea(Tarea tarea) throws DAOException, DuplicateKeyException;
    public void actualizarTareaEnDB(Tarea tarea) throws DAOException, EntityNotFoundExcepcion;
    public void asignarResponsable(String tituloTarea, Integer idResponsable) throws DAOException, EntityNotFoundExcepcion;
    public void eliminarTareaById(Integer id) throws DAOException, EntityNotFoundExcepcion;
    public Tarea obtenerTareaPorTitulo(String titulo) throws DAOException, EntityNotFoundExcepcion;

    //todo listar todas las tareas de un proyecto
}
