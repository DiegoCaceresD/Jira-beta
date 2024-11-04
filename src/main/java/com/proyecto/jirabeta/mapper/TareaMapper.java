package com.proyecto.jirabeta.mapper;

import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.DTOs.TareaDTO;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Tarea;

public class TareaMapper {
    EmpleadoMapper em = new EmpleadoMapper();
    TareaDTO tareaDTO = new TareaDTO();
    Tarea tarea = new Tarea();

    public TareaMapper() {
    }

    public TareaMapper(EmpleadoMapper em, TareaDTO tareaDTO, Tarea tarea) {
        this.em = em;
        this.tareaDTO = tareaDTO;
        this.tarea = tarea;
    }

    public Tarea tareaDtoToTarea(TareaDTO tareaDTO) {

        if (tareaDTO.getResponsable() != null){
            Empleado empleado = em.empleadoDtoToEmpleado(tareaDTO.getResponsable());
            tarea.setResponsable(empleado);
        }
        tarea.setHorasEstimadas(tareaDTO.getHorasEstimadas());
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setEstimacion(tareaDTO.getEstimacion());
        tarea.setPrioridad(tareaDTO.getPrioridad());
//        tarea.setProyecto(tareaDTO.setProyecto());
        return tarea;
    }

    public Tarea tareaToTareaDTO(Tarea tarea) {
        EmpleadoDTO empleadoDTO = em.empleadoToEmpleadoDto(tarea.getResponsable());
        tareaDTO.setHorasEstimadas(tarea.getHorasEstimadas());
        tareaDTO.setTitulo(tarea.getTitulo());
        tareaDTO.setDescripcion(tarea.getDescripcion());
        tareaDTO.setEstimacion(tarea.getEstimacion());
        tareaDTO.setPrioridad(tarea.getPrioridad());
        tareaDTO.setResponsable(empleadoDTO);
//        tarea.setProyecto(tareaDTO.setProyecto());
        return tarea;
    }

    public Tarea actualizarTareaEnMemoria(Tarea tareaToUpdate, TareaDTO nuevaTarea){

        if (nuevaTarea.getTitulo() != null){
            tareaToUpdate.setTitulo(nuevaTarea.getTitulo());
        }
        if (nuevaTarea.getDescripcion() != null){
            tareaToUpdate.setDescripcion(nuevaTarea.getDescripcion());
        }
        if (nuevaTarea.getEstimacion() != null){
            tareaToUpdate.setEstimacion(nuevaTarea.getEstimacion());
        }
        if (nuevaTarea.getPrioridad() != null){
            tareaToUpdate.setPrioridad(nuevaTarea.getPrioridad());
        }
        if (nuevaTarea.getResponsable() != null){
            Empleado empleado = em.empleadoDtoToEmpleado(tareaDTO.getResponsable());
            tareaToUpdate.setResponsable(empleado);
        }
        if (nuevaTarea.getProyecto() != null){
            tareaToUpdate.setProyecto(nuevaTarea.getProyecto());
        }

        tareaToUpdate.setHorasEstimadas(nuevaTarea.getHorasEstimadas());
        return tareaToUpdate;
    }
}
