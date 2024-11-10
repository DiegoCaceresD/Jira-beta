package com.proyecto.jirabeta;

import com.proyecto.jirabeta.DAOs.interfaces.SetUpTablesDAO;
import com.proyecto.jirabeta.DAOs.interfaces.TareaDAO;
import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.DTOs.TareaDTO;
import com.proyecto.jirabeta.connection.SetUpTablesH2impl;
import com.proyecto.jirabeta.enums.eEstimacion;
import com.proyecto.jirabeta.enums.ePrioridad;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.services.EmpleadoService;
import com.proyecto.jirabeta.services.ProyectoService;
import com.proyecto.jirabeta.services.TareaService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SetUpTablesDAO setUpTables = new SetUpTablesH2impl();
        setUpTables.crearTablas();
        ProyectoService proyectoService = new ProyectoService();
        TareaService tareaService = new TareaService();
        EmpleadoService empleadoService = new EmpleadoService();

        ResponseDTO proyectoRespo = proyectoService.obtenerProyectoByNombre("segundo Proyecto3");
        if (proyectoRespo.isSuccess()){
            System.out.println("PROYECTO RESPONSE: " +proyectoRespo.getData());
        }else {
            System.out.println(proyectoRespo.getErrorMsg());
        }

        ResponseDTO empleadoRespo = empleadoService.obtenerEmpleadoByDni("111111");
        if (proyectoRespo.isSuccess()){
            System.out.println("EMPLEADO RESPONSE: " +empleadoRespo.getData());
        }else {
            System.out.println(empleadoRespo.getErrorMsg());
        }

        ResponseDTO tareaRespo = tareaService.listarTareasByIdProyecto(1);
        if (tareaRespo.isSuccess()){
            System.out.println("TAREA RESPONSE: " + tareaRespo.getData());
        }else {
            System.out.println(tareaRespo.getErrorMsg());
        }


    }
}
