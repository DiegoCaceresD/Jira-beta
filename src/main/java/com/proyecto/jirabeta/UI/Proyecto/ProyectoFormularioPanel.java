package com.proyecto.jirabeta.UI.Proyecto;

import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.UI.Jira_beta_UI;
import com.proyecto.jirabeta.services.ProyectoService;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProyectoFormularioPanel {
    private ProyectoTableModel proyectoTableModel;
    private List<ProyectoDTO> proyectoList;
    private CardLayout cardLayout;
    private ProyectoService proyectoService;

    public JPanel createFormularioPanel(JPanel panelPrincipal, List<ProyectoDTO> proyectoList, ProyectoTableModel proyectoTableModel, CardLayout cardLayout) {
        this.proyectoList = proyectoList;
        this.proyectoTableModel = proyectoTableModel;
        this.cardLayout = cardLayout;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        proyectoService = new ProyectoService();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField txtNombre = new JTextField(20);
        JTextField txtFechaFin = new JTextField(20);

        panel.add(new JLabel("Nombre del Proyecto:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Fecha de Fin (YYYY-MM-DD):"));
        panel.add(txtFechaFin);

        JButton btnAceptar = new JButton("Aceptar");
        panel.add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String fechaFin = txtFechaFin.getText();

            if (!nombre.isEmpty() && !fechaFin.isEmpty()) {
                try {
                    Date fechaFinformatted = formatoFecha.parse(fechaFin);
                    ProyectoDTO nuevoProyecto = new ProyectoDTO(nombre, fechaFinformatted);
                    ResponseDTO responseDTO;
                    proyectoList.add(nuevoProyecto);
                    responseDTO = proyectoService.crearProyecto(nuevoProyecto);
                    if (responseDTO.isSuccess()){
                        proyectoTableModel.fireTableDataChanged();
                    }else {
                        JOptionPane.showMessageDialog(panelPrincipal, responseDTO.getErrorMsg());
                    }

                    cardLayout.show(panelPrincipal, "ListaProyectos");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, "Fecha inválida. Use el formato: yyyy-MM-dd");
                }
            } else {
                JOptionPane.showMessageDialog(panelPrincipal, "Por favor completa todos los campos.");
            }
        });
        return panel;
    }
}
