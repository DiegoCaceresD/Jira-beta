package com.proyecto.jirabeta.mapper;

import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.entities.Empleado;

public class EmpleadoMapper {
    ProyectoMapper pm = new ProyectoMapper();

    public EmpleadoMapper(ProyectoMapper pm) {
        this.pm = pm;
    }

    public EmpleadoMapper() {
    }

    public Empleado empleadoDtoToEmpleado(EmpleadoDTO empleadoDTO){
        Empleado empleado = new Empleado();
        empleado.setId(empleadoDTO.getId());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellido(empleadoDTO.getApellido());
        empleado.setDni(empleadoDTO.getDni());
        empleado.setEmail(empleadoDTO.getEmail());
        empleado.setCapacity(empleadoDTO.getCapacity());
        empleado.setDisponible(empleadoDTO.isDisponible());
        empleado.setProyecto(pm.proyectoDTOtoProyecto(empleadoDTO.getProyectoDTO()));
        return empleado;
    }

    public EmpleadoDTO empleadoToEmpleadoDto(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido(empleado.getApellido());
        empleadoDTO.setDni(empleado.getDni());
        empleadoDTO.setEmail(empleado.getEmail());
        empleadoDTO.setCapacity(empleado.getCapacity());
        empleadoDTO.setDisponible(empleado.isDisponible());
        empleadoDTO.setProyectoDTO(pm.proyectoToProyectoDTO(empleado.getProyecto()));
        return empleadoDTO;
    }

    public Empleado actualizarEmpleadoEnMemoria(Empleado empleado, EmpleadoDTO nuevoEmpleado){
        if (nuevoEmpleado.getId() != null){
            empleado.setId(nuevoEmpleado.getId());
        }
        if (nuevoEmpleado.getNombre() != null){
            empleado.setNombre(nuevoEmpleado.getNombre());
        }
        if (nuevoEmpleado.getApellido() != null){
            empleado.setApellido(nuevoEmpleado.getApellido());
        }
        if (nuevoEmpleado.getDni() != null){
            empleado.setDni(nuevoEmpleado.getDni());
        }
        if (nuevoEmpleado.getEmail() != null){
            empleado.setEmail(nuevoEmpleado.getEmail());
        }
        if (nuevoEmpleado.getCapacity() != 0){
            empleado.setCapacity(nuevoEmpleado.getCapacity());
        }
        if (nuevoEmpleado.getProyectoDTO() != null){
            empleado.setProyecto(pm.proyectoDTOtoProyecto(nuevoEmpleado.getProyectoDTO()));
        }
        return empleado;
    }
}
