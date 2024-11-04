package com.proyecto.jirabeta.DAOs.interfaces;

import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;

import java.util.List;

public interface ProyectoDAO {

    public void crearProyecto(Proyecto proyecto) throws DAOException, DuplicateKeyException;
    public void asignarTarea(Tarea tarea) throws  DAOException, EntityNotFoundExcepcion;
    public List<Proyecto> listarProyectos() throws DAOException;
    public Proyecto obtenerProyectoById(Integer id) throws DAOException, EntityNotFoundExcepcion;
    public Proyecto obtenerProyectoByNombre(String nombre) throws DAOException, EntityNotFoundExcepcion;

}