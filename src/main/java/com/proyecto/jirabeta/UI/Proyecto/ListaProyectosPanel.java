package com.proyecto.jirabeta.UI.Proyecto;

import com.proyecto.jirabeta.DTOs.ProyectoDTO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ListaProyectosPanel extends JPanel {
    private ProyectoTableModel proyectoTableModel;
    private List<ProyectoDTO> proyectoList;
    private CardLayout cardLayout;
    private JPanel panelPrincipal;
    ProyectoFormularioPanel proyectoFormularioPanel;

    public ListaProyectosPanel(List<ProyectoDTO> proyectoList, ProyectoTableModel proyectoTableModel, CardLayout cardLayout, JPanel panelPrincipal, ProyectoFormularioPanel proyectoFormularioPanel) {
        this.proyectoList = proyectoList;
        this.proyectoTableModel = proyectoTableModel;
        this.cardLayout = cardLayout;
        this.panelPrincipal = panelPrincipal;
        this.proyectoFormularioPanel = proyectoFormularioPanel;
        crearListaProyectosPanel();
    }

    private void crearListaProyectosPanel() {
        setLayout(new BorderLayout());

        JTable tablaProyectos = new JTable(proyectoTableModel);
        JScrollPane scrollPane = new JScrollPane(tablaProyectos);
        add(scrollPane, BorderLayout.CENTER);

        JLabel titulo = new JLabel("Lista de Proyectos", JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar Proyecto");
        JButton btnEliminar = new JButton("Eliminar Proyecto");
        JButton btnModificar = new JButton("Modificar Proyecto");

        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        add(panelBotones, BorderLayout.SOUTH);

        //acciones
        btnAgregar.addActionListener(e -> {
            cardLayout.show(panelPrincipal, "GestionarProyecto");
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = tablaProyectos.getSelectedRow();
            if (selectedRow != -1) {
                proyectoTableModel.removeProyecto(selectedRow, panelPrincipal);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un proyecto para eliminar");
            }
        });
        btnModificar.addActionListener(e -> {
            int selectedRow = tablaProyectos.getSelectedRow();
            if (selectedRow != -1) {
                ProyectoDTO proyectoSeleccionado = proyectoList.get(selectedRow);
                proyectoFormularioPanel.cargarProyecto(proyectoSeleccionado);
                cardLayout.show(panelPrincipal, "FormularioProyecto");
            }
        });

        tablaProyectos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean isRowSelected = tablaProyectos.getSelectedRow() != -1;
                btnEliminar.setEnabled(isRowSelected);
                btnModificar.setEnabled(isRowSelected);
            }
        });
    }
}
