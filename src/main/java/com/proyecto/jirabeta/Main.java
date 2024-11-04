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

        ResponseDTO responseDTO = new ResponseDTO();
        ProyectoDTO proyectoDTO = new ProyectoDTO();
        proyectoDTO.setNombre("segundo Proyecto");

        ProyectoService proyectoService = new ProyectoService();
       responseDTO = proyectoService.crearProyecto(proyectoDTO);
        if (responseDTO.isSuccess()){
            System.out.println(responseDTO.getData() + " creado");
        }else {
            System.out.println(responseDTO.getErrorMsg());
        }
    }
}
