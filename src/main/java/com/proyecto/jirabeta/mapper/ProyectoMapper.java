package com.proyecto.jirabeta.mapper;

import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.entities.Proyecto;

public class ProyectoMapper {
    Proyecto proyecto = new Proyecto();

    public ProyectoMapper() {
    }

    public ProyectoMapper(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto proyectoDTOtoProyecto(ProyectoDTO proyectoDTO) {
        if (proyectoDTO.getEmpleados() != null) {
            proyecto.setEmpleados(proyectoDTO.getEmpleados());
        }
        if (proyectoDTO.getTareas() != null) {
            proyecto.setTareas(proyectoDTO.getTareas());
        }
        proyecto.setFechaFin(proyectoDTO.getFechaFin());
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setEstado(proyectoDTO.getEstado());
        return proyecto;
    }

    public ProyectoDTO proyectoToProyectoDTO(Proyecto proyecto) {
        ProyectoDTO proyectoDTO = new ProyectoDTO();
        if (proyecto.getEmpleados() != null) {
            proyectoDTO.setEmpleados(proyecto.getEmpleados());
        }
        if (proyecto.getTareas() != null) {
            proyectoDTO.setTareas(proyecto.getTareas());
        }
        proyectoDTO.setId(proyecto.getId());
        proyectoDTO.setNombre(proyecto.getNombre());
        proyectoDTO.setEstado(proyecto.getEstado());
        proyectoDTO.setFechaFin(proyecto.getFechaFin());
        return proyectoDTO;
    }

    public Proyecto actualizarProyectoEnMemoria(Proyecto proyectoToUpdate, ProyectoDTO nuevoProyecto) {
        if (nuevoProyecto.getId() != null) {
            proyectoToUpdate.setId(nuevoProyecto.getId());
        }
        if (nuevoProyecto.getNombre() != null) {
            proyectoToUpdate.setNombre(nuevoProyecto.getNombre());
        }
        if (nuevoProyecto.getEmpleados() != null) {
            proyectoToUpdate.setEmpleados(nuevoProyecto.getEmpleados());
        }
        if (nuevoProyecto.getTareas() != null) {
            proyectoToUpdate.setTareas(nuevoProyecto.getTareas());
        }
        if (nuevoProyecto.getEstado() != null) {
            proyectoToUpdate.setEstado(nuevoProyecto.getEstado());
        }
        return proyectoToUpdate;
    }
}
