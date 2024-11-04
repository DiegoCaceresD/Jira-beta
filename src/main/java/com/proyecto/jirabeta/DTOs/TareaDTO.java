package com.proyecto.jirabeta.DTOs;

import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.enums.eEstimacion;
import com.proyecto.jirabeta.enums.ePrioridad;

public class TareaDTO {
    private Integer id;
    private float horasEstimadas;
    private String titulo;
    private String descripcion;
    private eEstimacion estimacion;
    private ePrioridad prioridad;
    private EmpleadoDTO responsable;
    private Proyecto proyecto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public EmpleadoDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(EmpleadoDTO responsable) {
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
        return "TareaDTO{" +
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