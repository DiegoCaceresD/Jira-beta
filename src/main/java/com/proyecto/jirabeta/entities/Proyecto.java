package com.proyecto.jirabeta.entities;

import java.util.List;

public class Proyecto {
    private long id;
    private String nombre;
    private List<Tarea> tareas;
    private List<Empleado> empleados;

    public Proyecto(long id, String nombre, List<Tarea> tarea, List<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.tareas = tarea;
        this.empleados = empleados;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
