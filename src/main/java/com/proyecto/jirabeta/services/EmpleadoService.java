package com.proyecto.jirabeta.services;

import com.proyecto.jirabeta.DAOs.EmpleadoDAOH2impl;
import com.proyecto.jirabeta.DAOs.interfaces.EmpleadoDAO;
import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.mapper.EmpleadoMapper;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    public ResponseDTO crearEmpleado(EmpleadoDTO empleadoDTO)  {
        ResponseDTO responseDTO = new ResponseDTO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAOH2impl();
        Empleado empleado;
        EmpleadoMapper empleadoMapper = new EmpleadoMapper();
        try {
            empleado = empleadoMapper.empleadoDtoToEmpleado(empleadoDTO);
            empleadoDAO.crearEmpleado(empleado);
            responseDTO.setData(empleado);
            responseDTO.setSuccess(true);
        } catch (DuplicateKeyException | DAOException e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO actualizarEmpleado(EmpleadoDTO empleadoDTO)  {
        ResponseDTO responseDTO = new ResponseDTO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAOH2impl();
        Empleado empleado;
        EmpleadoMapper empleadoMapper = new EmpleadoMapper();
        try {
            empleado = empleadoMapper.empleadoDtoToEmpleado(empleadoDTO);
            empleadoDAO.actualizarEmpleado(empleado);
            responseDTO.setData(empleadoDTO.getDni());
            responseDTO.setSuccess(true);
        } catch (EntityNotFoundExcepcion | DAOException e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    public ResponseDTO eliminarEmpleadoById(long id)  {
        ResponseDTO responseDTO = new ResponseDTO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAOH2impl();
        try {
            empleadoDAO.eliminarEmpleadoById(id);
            responseDTO.setData(id);
            responseDTO.setSuccess(true);
        } catch (EntityNotFoundExcepcion | DAOException e) {
            responseDTO.setErrorMsg(e.getMessage());
            responseDTO.setSuccess(false);
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO listarEmpleados() throws DAOException {
        ResponseDTO responseDTO = new ResponseDTO();
        EmpleadoDAO empleadoDAO = new EmpleadoDAOH2impl();
        List<EmpleadoDTO> empleadoDTOList = new ArrayList<>();
        EmpleadoMapper empleadoMapper = new EmpleadoMapper();
        try {
            List<Empleado> empleadoList = empleadoDAO.listarEmpleados();
            for (Empleado em: empleadoList) {
                empleadoDTOList.add(empleadoMapper.empleadoToEmpleadoDto(em));
            }
            responseDTO.setData(empleadoDTOList);
            responseDTO.setSuccess(true);
        } catch (DAOException d) {
            responseDTO.setErrorMsg(d.getMessage());
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

}
