package com.proyecto.jirabeta.services;

import com.proyecto.jirabeta.DAOs.ProyectoDAOH2impl;
import com.proyecto.jirabeta.DAOs.interfaces.ProyectoDAO;
import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.connection.DBManager;
import com.proyecto.jirabeta.entities.Proyecto;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.mapper.ProyectoMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProyectoService {

    ProyectoDAO proyectoDAO = new ProyectoDAOH2impl();
    ProyectoMapper proyectoMapper = new ProyectoMapper();

    public ProyectoService() {
    }

    public ProyectoService(ProyectoDAO proyectoDAO, ProyectoMapper proyectoMapper) {
        this.proyectoDAO = proyectoDAO;
        this.proyectoMapper = proyectoMapper;
    }

    public ResponseDTO crearProyecto(ProyectoDTO proyectoDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        Proyecto proyecto;
        try {
            proyecto = proyectoMapper.proyectoDTOtoProyecto(proyectoDTO);
            proyectoDAO.crearProyecto(proyecto);
            responseDTO.setData(proyecto.getNombre());
            responseDTO.setSuccess(true);
        }catch (DAOException | DuplicateKeyException e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }
}
