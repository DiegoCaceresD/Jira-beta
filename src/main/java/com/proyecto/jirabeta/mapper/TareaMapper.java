package com.proyecto.jirabeta.mapper;

import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.DTOs.TareaDTO;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Tarea;

public class TareaMapper {
    EmpleadoMapper em = new EmpleadoMapper();
    ProyectoMapper pm = new ProyectoMapper();
    TareaDTO tareaDTO = new TareaDTO();
    Tarea tarea = new Tarea();

    public TareaMapper() {
    }

    public TareaMapper(EmpleadoMapper em, ProyectoMapper pm, TareaDTO tareaDTO, Tarea tarea) {
        this.em = em;
        this.pm = pm;
        this.tareaDTO = tareaDTO;
        this.tarea = tarea;
    }

    public Tarea tareaDtoToTarea(TareaDTO tareaDTO) {

        if (tareaDTO.getResponsableDTO() != null){
            Empleado empleado = em.empleadoDtoToEmpleado(tareaDTO.getResponsableDTO());
            tarea.setResponsable(empleado);
        }
        tarea.setHorasEstimadas(tareaDTO.getHorasEstimadas());
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setEstimacion(tareaDTO.getEstimacion());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        tarea.setProyecto(pm.proyectoDTOtoProyecto(tareaDTO.getProyectoDTO()));
        return tarea;
    }

    public TareaDTO tareaToTareaDTO(Tarea tarea) {
        EmpleadoDTO empleadoDTO = em.empleadoToEmpleadoDto(tarea.getResponsable());
        tareaDTO.setHorasEstimadas(tarea.getHorasEstimadas());
        tareaDTO.setTitulo(tarea.getTitulo());
        tareaDTO.setDescripcion(tarea.getDescripcion());
        tareaDTO.setEstimacion(tarea.getEstimacion());
        tareaDTO.setPrioridad(tarea.getPrioridad());
        tareaDTO.setResponsableDTO(empleadoDTO);
        tareaDTO.setProyectoDTO(pm.proyectoToProyectoDTO(tarea.getProyecto()));
        return tareaDTO;
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
        if (nuevaTarea.getResponsableDTO() != null){
            Empleado empleado = em.empleadoDtoToEmpleado(tareaDTO.getResponsableDTO());
            tareaToUpdate.setResponsable(empleado);
        }
        if (nuevaTarea.getProyectoDTO() != null){
            tareaToUpdate.setProyecto(pm.proyectoDTOtoProyecto(nuevaTarea.getProyectoDTO()));
        }

        tareaToUpdate.setHorasEstimadas(nuevaTarea.getHorasEstimadas());
        return tareaToUpdate;
    }
}
