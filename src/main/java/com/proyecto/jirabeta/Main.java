package com.proyecto.jirabeta;

import com.proyecto.jirabeta.DAOs.interfaces.SetUpTablesDAO;
import com.proyecto.jirabeta.DAOs.interfaces.TareaDAO;
import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.DTOs.TareaDTO;
import com.proyecto.jirabeta.connection.SetUpTablesH2impl;
import com.proyecto.jirabeta.enums.eEstimacion;
import com.proyecto.jirabeta.enums.ePrioridad;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.services.EmpleadoService;
import com.proyecto.jirabeta.services.TareaService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SetUpTablesDAO setUpTables = new SetUpTablesH2impl();
        setUpTables.crearTablas();
//        EmpleadoService empleadoService = new EmpleadoService();
//        TareaService tareaService = new TareaService();
//        ResponseDTO responseDTO;

//        responseDTO = empleadoService.obtenerEmpleadoByDni("3333");
//        if (responseDTO.isSuccess()) {
//            EmpleadoDTO empleadoDTO = (EmpleadoDTO) responseDTO.getData();
//            TareaDTO tareaDTO = new TareaDTO();
//            tareaDTO.setTitulo("prueba3");
//            tareaDTO.setHorasEstimadas(10);
//            tareaDTO.setDescripcion("tarea con responsable");
//            tareaDTO.setPrioridad(ePrioridad.HIGH);
//            tareaDTO.setEstimacion(eEstimacion.LARGE);
//            tareaDTO.setResponsable(empleadoDTO);
            // tareaDTO.setProyecto(null);

//            responseDTO = tareaService.crearTarea(tareaDTO);
//            if (responseDTO.isSuccess()) {
//                System.out.println("Tarea Creada exitosamente" + responseDTO.getData());
//            } else {
//                System.out.println(responseDTO.getErrorMsg());
//            }
//        } else {
//            System.out.println(responseDTO.getErrorMsg());
//        }

        //ACTUALIZAR TAREA
//       responseDTO = tareaService.modificarTarea(tareaDTO);
//       if (responseDTO.isSuccess()){
//           System.out.println("Tarea actualizada correctamente. Titulo: " + responseDTO.getData());
//       }else {
//           System.out.println( responseDTO.getErrorMsg());
//       }

////        ASIGNAR RESPONSABLE
//        responseDTO = tareaService.asignarResponsable("prueba3", 10);
//        if (responseDTO.isSuccess()){
//            System.out.println(responseDTO.getData());
//        }else {
//            System.out.println(responseDTO.getErrorMsg());
//        }

        //Elimiar TArea
//        responseDTO = tareaService.eliminarTareaById(9);
//        if (responseDTO.isSuccess()){
//            System.out.println("La tarea " +  responseDTO.getData() + " se elimino correctamente");
//        }else {
//            System.out.println(responseDTO.getErrorMsg());
//        }
    }
}
