package com.proyecto.jirabeta.entities;

import java.util.List;

public class Proyecto {
    private Integer id;
    private String nombre;
    private List<Tarea> tareas;
    private List<Empleado> empleados;

    public Proyecto(Integer id, String nombre, List<Tarea> tarea, List<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.tareas = tarea;
        this.empleados = empleados;
    }

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
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tarea=" + tareas +
                ", empleados=" + empleados +
                '}';
    }
}
