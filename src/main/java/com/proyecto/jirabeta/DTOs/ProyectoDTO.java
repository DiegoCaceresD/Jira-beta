package com.proyecto.jirabeta.DTOs;

import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.enums.eEstados;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProyectoDTO {
    private Integer id;
    private String nombre;
    private List<Tarea> tareas;
    private List<Empleado> empleados;
    private eEstados estado;
    private Date fechaFin;

    public ProyectoDTO(String nombre, Date fechaFin) {
        this.nombre = nombre;
        this.fechaFin = fechaFin;
        this.tareas = new ArrayList<>();
        this.empleados = new ArrayList<>();
        if (this.getEstado() == null) this.setEstado(eEstados.EN_ESPERA);
    }
    public ProyectoDTO(){

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

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
