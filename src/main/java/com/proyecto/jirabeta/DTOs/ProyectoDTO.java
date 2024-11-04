package com.proyecto.jirabeta.DTOs;

import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Tarea;

import java.util.List;

public class ProyectoDTO {
    private Integer id;
    private String nombre;
    private List<Tarea> tareas;
    private List<Empleado> empleados;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "ProyectoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tareas=" + tareas +
                ", empleados=" + empleados +
                '}';
    }
}
