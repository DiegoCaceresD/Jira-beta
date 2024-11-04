package com.proyecto.jirabeta.DAOs.interfaces;

import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.entities.Empleado;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;

import java.util.List;

public interface EmpleadoDAO {

    void crearEmpleado(Empleado empleado) throws DAOException, DuplicateKeyException;

    void eliminarEmpleadoById(Integer id) throws DAOException, EntityNotFoundExcepcion;

    void actualizarEmpleado(Empleado empleado) throws DAOException, EntityNotFoundExcepcion;

    Empleado obtenerEmpleadoByDni (String dni) throws DAOException, EntityNotFoundExcepcion;
    Empleado obtenerEmpleadoById (Integer id) throws DAOException, EntityNotFoundExcepcion;

    List<Empleado> listarEmpleados () throws DAOException;


}
