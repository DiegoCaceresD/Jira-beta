package com.proyecto.jirabeta.UI.Proyecto;

import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.enums.eEstados;
import com.proyecto.jirabeta.services.ProyectoService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProyectoTableModel extends AbstractTableModel {
    private List<ProyectoDTO> proyectoList;
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_NOMBRE = 1;
    private static final int COLUMNA_CANT_TAREAS = 2;
    private static final int COLUMNA_CANT_EMPLEADOS = 3;
    private static final int COLUMNA_ESTADO = 4;
    private static final int COLUMNA_FECHA_FIN = 5;
    private String[] nombresColumnas = {"ID", "Nombre", "Cantidad de Tareas", "Cantidad de Empleados", "Estado", "Fecha fin"};
    private Class[] tiposColumnas = {Integer.class, String.class, Integer.class, Integer.class, eEstados.class, Date.class};

    public ProyectoTableModel(List<ProyectoDTO> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public ProyectoTableModel() {
        proyectoList = new ArrayList<ProyectoDTO>();
    }

    @Override
    public int getRowCount() {
        return proyectoList.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProyectoDTO u = proyectoList.get(rowIndex);

        Object result = null;
        switch (columnIndex) {
            case COLUMNA_ID:
                result = u.getId();
                break;
            case COLUMNA_NOMBRE:
                result = u.getNombre();
                break;
            case COLUMNA_CANT_TAREAS:
                result = u.getTareas().size();
                break;
            case COLUMNA_CANT_EMPLEADOS:
                result = u.getEmpleados().size();
                break;
            case COLUMNA_ESTADO:
                result = u.getEstado();
                break;
            case COLUMNA_FECHA_FIN:
                result = u.getFechaFin();
                break;
            default:
                result = new String("");
        }

        return result;
    }

    public String getColumnName(int col) {
        return nombresColumnas[col];
    }

    public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }

    public void removeProyecto(int rowIndex, JPanel panelPrincipal) {
        ResponseDTO responseDTO;
        ProyectoService proyectoService = new ProyectoService();
        ProyectoDTO proyectoDTO = proyectoList.get(rowIndex);
        responseDTO = proyectoService.eliminarProyectoById(proyectoDTO.getId());
        if (responseDTO.isSuccess()) {
            proyectoList.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        } else {
            JOptionPane.showMessageDialog(panelPrincipal, responseDTO.getErrorMsg());
        }
    }

}
