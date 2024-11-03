package com.proyecto.jirabeta;

import com.proyecto.jirabeta.DAOs.interfaces.SetUpTablesDAO;
import com.proyecto.jirabeta.DTOs.EmpleadoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.connection.SetUpTablesH2impl;
import com.proyecto.jirabeta.exceptions.DAOException;
import com.proyecto.jirabeta.exceptions.DuplicateKeyException;
import com.proyecto.jirabeta.exceptions.EntityNotFoundExcepcion;
import com.proyecto.jirabeta.services.EmpleadoService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SetUpTablesDAO setUpTables = new SetUpTablesH2impl();
        setUpTables.crearTablas();

    }
}
