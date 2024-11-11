package com.proyecto.jirabeta.entities;

import com.proyecto.jirabeta.enums.eEstados;

import java.util.Date;
import java.util.List;

public class Proyecto {
    private Integer id;
    private String nombre;
    private List<Tarea> tareas;
    private List<Empleado> empleados;
    private eEstados estado;
    private Date fechaFin;

    public Proyecto(String nombre, List<Tarea> tareas, List<Empleado> empleados) {
        this.nombre = nombre;
        this.tareas = tareas;
        this.empleados = empleados;
    }

    public Proyecto(){

    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public eEstados getEstado() {
        return estado;
    }

    public void setEstado(eEstados estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tareas=" + tareas +
                ", empleados=" + empleados +
                ", estado=" + estado +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
