package com.proyecto.jirabeta.services;

import com.proyecto.jirabeta.DAOs.TareaDAOH2impl;
import com.proyecto.jirabeta.DAOs.interfaces.TareaDAO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.DTOs.TareaDTO;
import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.mapper.TareaMapper;

import java.util.ArrayList;
import java.util.List;


public class TareaService {
    TareaDAO tareaDAO = new TareaDAOH2impl();
    TareaMapper tareaMapper = new TareaMapper();

    public TareaService() {
    }

    public TareaService(TareaDAO tareaDAO, TareaMapper tareaMapper) {
        this.tareaDAO = tareaDAO;
        this.tareaMapper = tareaMapper;
    }

    public ResponseDTO crearTarea(TareaDTO tareaDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        Tarea tarea;
        try {
            tarea = tareaMapper.tareaDtoToTarea(tareaDTO);
            tareaDAO.crearTarea(tarea);
            responseDTO.setData(tarea);
            responseDTO.setSuccess(true);
        } catch (DuplicateKeyException | DAOException | EntityNotFoundExcepcion e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO modificarTarea(TareaDTO tareaDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        Tarea tarea;
        try {
            Tarea tareaToUpdate = tareaDAO.obtenerTareaPorTitulo(tareaDTO.getTitulo());
            tarea = tareaMapper.actualizarTareaEnMemoria(tareaToUpdate, tareaDTO);
            tareaDAO.actualizarTareaEnDB(tarea);
            responseDTO.setSuccess(true);
            responseDTO.setData(tarea.getTitulo());
        } catch (DAOException | EntityNotFoundExcepcion e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    public ResponseDTO asignarResponsable(String titulo, Integer idResponsable) {
        ResponseDTO responseDTO = new ResponseDTO();
        EmpleadoService empleadoService = new EmpleadoService();
        try {
            responseDTO = empleadoService.obtenerEmpleadoById(idResponsable);
            if (responseDTO.isSuccess()) {
                tareaDAO.asignarResponsable(titulo, idResponsable);
                responseDTO.setData("Se asigno correctamente el Responsable ID: " + idResponsable + " a la tarea: " + titulo);
                responseDTO.setSuccess(true);
            } else {
                //Este response ya viene con el error desde EmpleadosService
                return responseDTO;
            }
        } catch (DAOException | EntityNotFoundExcepcion e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMsg(e.getMessage());
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO eliminarTareaById(Integer idTarea) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            tareaDAO.eliminarTareaById(idTarea);
            responseDTO.setData(idTarea);
            responseDTO.setSuccess(true);
        } catch (DAOException | EntityNotFoundExcepcion e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMsg(e.getMessage());
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO listarTareasByIdProyecto(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Tarea> tareaList = new ArrayList<>();

        try {
            tareaList = tareaDAO.listarTareasByIdProyecto(id);
            responseDTO.setData(tareaList);
            responseDTO.setSuccess(true);
        } catch (DAOException | EntityNotFoundExcepcion e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMsg(e.getMessage());
            return responseDTO;
        }
        return responseDTO;
    }
}
