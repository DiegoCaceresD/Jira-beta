package com.proyecto.jirabeta;

import com.proyecto.jirabeta.DAOs.interfaces.SetUpTablesDAO;
import com.proyecto.jirabeta.UI.Jira_beta_UI;
import com.proyecto.jirabeta.connection.SetUpTablesH2impl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SetUpTablesDAO setUpTables = new SetUpTablesH2impl();
        setUpTables.crearTablas();

        SwingUtilities.invokeLater(Jira_beta_UI::new);
//        ProyectoService proyectoService = new ProyectoService();
//        TareaService tareaService = new TareaService();
//        EmpleadoService empleadoService = new EmpleadoService();
//
//        ResponseDTO proyectoRespo = proyectoService.obtenerProyectoByNombre("segundo Proyecto3");
//        if (proyectoRespo.isSuccess()){
//            System.out.println("PROYECTO RESPONSE: " +proyectoRespo.getData());
//        }else {
//            System.out.println(proyectoRespo.getErrorMsg());
//        }
//
//        ResponseDTO empleadoRespo = empleadoService.obtenerEmpleadoByDni("111111");
//        if (proyectoRespo.isSuccess()){
//            System.out.println("EMPLEADO RESPONSE: " +empleadoRespo.getData());
//        }else {
//            System.out.println(empleadoRespo.getErrorMsg());
//        }
//
//        ResponseDTO tareaRespo = tareaService.listarTareasByIdProyecto(1);
//        if (tareaRespo.isSuccess()){
//            System.out.println("TAREA RESPONSE: " + tareaRespo.getData());
//        }else {
//            System.out.println(tareaRespo.getErrorMsg());
//        }

//        JFrame j = new JFrame("Jira-beta");
//        j.getContentPane().add(new TablaProyectosPanel());
//        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        j.pack();
//        j.setVisible(true);

    }
}
