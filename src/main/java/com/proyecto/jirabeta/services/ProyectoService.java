package com.proyecto.jirabeta.services;

import com.proyecto.jirabeta.DAOs.EmpleadoDAOH2impl;
import com.proyecto.jirabeta.DAOs.ProyectoDAOH2impl;
import com.proyecto.jirabeta.DAOs.TareaDAOH2impl;
import com.proyecto.jirabeta.DAOs.interfaces.EmpleadoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.ProyectoDAO;
import com.proyecto.jirabeta.DAOs.interfaces.TareaDAO;
import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.entities.Tarea;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.mapper.ProyectoMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProyectoService {

    ProyectoDAO proyectoDAO = new ProyectoDAOH2impl();
    EmpleadoDAO empleadoDAO = new EmpleadoDAOH2impl();
    TareaDAO tareaDAO = new TareaDAOH2impl();
    ProyectoMapper proyectoMapper = new ProyectoMapper();

    public ProyectoService() {
    }

    public ProyectoService(ProyectoDAO proyectoDAO, EmpleadoDAO empleadoDAO, TareaDAO tareaDAO, ProyectoMapper proyectoMapper) {
        this.proyectoDAO = proyectoDAO;
        this.empleadoDAO = empleadoDAO;
        this.tareaDAO = tareaDAO;
        this.proyectoMapper = proyectoMapper;
    }

    public ResponseDTO crearProyecto(ProyectoDTO proyectoDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        Proyecto proyecto;
        try {
            proyecto = proyectoMapper.proyectoDTOtoProyecto(proyectoDTO);
            proyectoDAO.crearProyecto(proyecto);
            responseDTO.setData(proyecto.getNombre());
            responseDTO.setSuccess(true);
        } catch (DAOException | DuplicateKeyException e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    public ResponseDTO listarProyectos() {
        List<ProyectoDTO> proyectoDTOList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<Proyecto> proyectoList = proyectoDAO.listarProyectos();
            for (Proyecto pr : proyectoList) {
                List<Empleado> empleadoList = empleadoDAO.listarEmpleadosByIdProyecto(pr.getId());
                pr.setEmpleados(empleadoList);
                List<Tarea> tareaList = tareaDAO.listarTareasByIdProyecto(pr.getId());
                pr.setTareas(tareaList);
                proyectoDTOList.add(proyectoMapper.proyectoToProyectoDTO(pr));
            }
            responseDTO.setData(proyectoDTOList);
            responseDTO.setSuccess(true);
        } catch (DAOException | EntityNotFoundExcepcion d) {
            responseDTO.setErrorMsg(d.getMessage());
            responseDTO.setSuccess(false);
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO obtenerProyectoById(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProyectoDTO proyectoDTO;
        try {
            Proyecto proyecto = proyectoDAO.obtenerProyectoById(id);
            proyecto.setTareas(tareaDAO.listarTareasByIdProyecto(id));
            proyecto.setEmpleados(empleadoDAO.listarEmpleadosByIdProyecto(id));
            proyectoDTO = proyectoMapper.proyectoToProyectoDTO(proyecto);
            responseDTO.setData(proyectoDTO);
            responseDTO.setSuccess(true);
        } catch (DAOException | EntityNotFoundExcepcion e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }
        public ResponseDTO obtenerProyectoByNombre(String nombre) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProyectoDTO proyectoDTO;
        try {
            Proyecto proyecto = proyectoDAO.obtenerProyectoByNombre(nombre);
            proyecto.setTareas(tareaDAO.listarTareasByIdProyecto(proyecto.getId()));
            proyecto.setEmpleados(empleadoDAO.listarEmpleadosByIdProyecto(proyecto.getId()));
            proyectoDTO = proyectoMapper.proyectoToProyectoDTO(proyecto);
            responseDTO.setData(proyectoDTO);
            responseDTO.setSuccess(true);
        } catch (DAOException | EntityNotFoundExcepcion e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    public ResponseDTO eliminarProyectoById(Integer id){
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            proyectoDAO.eliminarProyectById(id);
            responseDTO.setData(id);
            responseDTO.setSuccess(true);
        }catch (EntityNotFoundExcepcion | DAOException e){
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
            return responseDTO;
        }
        return responseDTO;
    }
}
