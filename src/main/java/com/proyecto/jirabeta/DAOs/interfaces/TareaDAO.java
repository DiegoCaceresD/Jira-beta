package com.proyecto.jirabeta.DAOs.interfaces;

import com.proyecto.jirabeta.entities.Tarea;

public interface TareaDAO {

    public void crearTarea();
    public void modificarTarea();
    public void asignarResponsable();
    public void eliminarTarea();
    public Tarea obtenerTareaPorId();
}
