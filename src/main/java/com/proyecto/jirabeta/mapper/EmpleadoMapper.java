package com.proyecto.jirabeta.mapper;

import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.entities.Empleado;

public class EmpleadoMapper {

    public Empleado empleadoDtoToEmpleado(EmpleadoDTO empleadoDTO){
        Empleado empleado = new Empleado();
        empleado.setId(empleadoDTO.getId());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellido(empleadoDTO.getApellido());
        empleado.setDni(empleadoDTO.getDni());
        empleado.setEmail(empleadoDTO.getEmail());
        empleado.setCapacity(empleadoDTO.getCapacity());
        empleado.setDisponible(empleadoDTO.isDisponible());
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
        return empleadoDTO;
    }
}
