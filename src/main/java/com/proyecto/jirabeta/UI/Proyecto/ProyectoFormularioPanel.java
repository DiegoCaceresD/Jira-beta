package com.proyecto.jirabeta.UI.Proyecto;

import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.enums.eEstados;
import com.proyecto.jirabeta.services.ProyectoService;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProyectoFormularioPanel extends JPanel {
    private ProyectoTableModel proyectoTableModel;
    private List<ProyectoDTO> proyectoList;
    private CardLayout cardLayout;
    private ProyectoService proyectoService;
    private JPanel panelPrincipal;
    JTextField txtNombre;
    JTextField txtFechaFin;
    JComboBox<eEstados> comboEstado;
    ProyectoDTO proyectoDTO;

    public ProyectoFormularioPanel(List<ProyectoDTO> proyectoList, ProyectoTableModel proyectoTableModel, CardLayout cardLayout, JPanel panelPrincipal) {
        this.proyectoList = proyectoList;
        this.proyectoTableModel = proyectoTableModel;
        this.cardLayout = cardLayout;
        this.panelPrincipal = panelPrincipal;
        this.proyectoService = new ProyectoService();
        createFormularioPanel();
    }

    private void createFormularioPanel() {

        setLayout(new BorderLayout());
        JPanel panelFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtNombre = new JTextField(20);
        txtFechaFin = new JTextField(20);
        comboEstado = new JComboBox<>(eEstados.values());

        panelFormulario.add(new JLabel("Nombre del Proyecto:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Fecha de Fin (YYYY-MM-DD):"));
        panelFormulario.add(txtFechaFin);
        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(comboEstado);
        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnVolver = new JButton("Volver");

        panelBotones.add(btnAceptar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        btnAceptar.addActionListener(e -> {
            persistirProyecto();
        });

        btnVolver.addActionListener(e -> {
            cardLayout.show(panelPrincipal, "ListaProyectos");
        });
    }

    private void persistirProyecto() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String nombre = txtNombre.getText();
        String fechaFin = txtFechaFin.getText();
        ResponseDTO responseDTO;

        if (!nombre.isEmpty() && !fechaFin.isEmpty()) {
            try {
                Date fechaFinformatted = formatoFecha.parse(fechaFin);
                if (proyectoDTO == null) {
                    ProyectoDTO nuevoProyecto = new ProyectoDTO(nombre, fechaFinformatted);
                    responseDTO = proyectoService.crearProyecto(nuevoProyecto);
                    if (responseDTO.isSuccess()) {
                        proyectoList.add((ProyectoDTO) responseDTO.getData());
                    } else {
                        JOptionPane.showMessageDialog(panelPrincipal, responseDTO.getErrorMsg());
                    }
                } else {
                    eEstados nuevoEstado = (eEstados)comboEstado.getSelectedItem();
                    proyectoDTO.setNombre(nombre);
                    proyectoDTO.setFechaFin(formatoFecha.parse(fechaFin));
                    proyectoDTO.setEstado(nuevoEstado);
                    responseDTO = proyectoService.actualizarProyecto(proyectoDTO);
                    if (responseDTO.isSuccess()) {
                        proyectoDTO = (ProyectoDTO) responseDTO.getData();
                        int index = proyectoList.indexOf(proyectoDTO);
                        proyectoList.set(index, ((ProyectoDTO) responseDTO.getData()));
                    } else {
                        JOptionPane.showMessageDialog(panelPrincipal, responseDTO.getErrorMsg());
                    }
                }
                proyectoTableModel.fireTableDataChanged();
                limpiarFormulario();
                cardLayout.show(panelPrincipal, "ListaProyectos");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(panelPrincipal, "Fecha inválida. Use el formato: yyyy-MM-dd");
            }
        } else {
            JOptionPane.showMessageDialog(panelPrincipal, "Por favor completa todos los campos.");
        }
    }

    public void cargarProyecto(ProyectoDTO proyectoDTO) {
        this.proyectoDTO = proyectoDTO;
        if (proyectoDTO != null){
            txtNombre.setText(proyectoDTO.getNombre());
            txtFechaFin.setText(new SimpleDateFormat("yyyy-MM-dd").format(proyectoDTO.getFechaFin()));
            comboEstado.setSelectedItem(proyectoDTO.getEstado());
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtFechaFin.setText("");
        comboEstado.setSelectedIndex(0);
        proyectoDTO = null;
    }
}
