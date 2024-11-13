package com.proyecto.jirabeta;

import com.proyecto.jirabeta.DAOs.interfaces.SetUpTablesDAO;
import com.proyecto.jirabeta.UI.Jira_beta_UI;
import com.proyecto.jirabeta.connection.SetUpTablesH2impl;
import com.proyecto.jirabeta.exceptions.DAOException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SetUpTablesDAO setUpTables = new SetUpTablesH2impl();
        try {
            setUpTables.crearTablas();
        } catch (DAOException d) {
            JOptionPane.showMessageDialog(null,
                    "Error al crear las tablas en la base de datos",
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Crea y muestra la interfaz de usuario de forma segura en el hilo de eventos de Swing
        SwingUtilities.invokeLater(Jira_beta_UI::new);
    }
}
