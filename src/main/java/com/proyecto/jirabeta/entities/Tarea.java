package com.proyecto.jirabeta.entities;

import com.proyecto.jirabeta.enums.eEstimacion;
import com.proyecto.jirabeta.enums.ePrioridad;

public class Tarea {
    private long id;
    private float horasEstimadas;
    private String titulo;
    private String descripcion;
    private eEstimacion estimacion;
    private ePrioridad prioridad;
    private Empleado responsable;
    private Proyecto proyecto;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(float horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public eEstimacion getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(eEstimacion estimacion) {
        this.estimacion = estimacion;
    }

    public ePrioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(ePrioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", horasEstimadas=" + horasEstimadas +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estimacion=" + estimacion +
                ", prioridad=" + prioridad +
                ", responsable=" + responsable +
                ", proyecto=" + proyecto +
                '}';
    }
}
