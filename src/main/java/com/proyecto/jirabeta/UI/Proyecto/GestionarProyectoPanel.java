package com.proyecto.jirabeta.UI.Proyecto;
import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.UI.Interfaces.Igestionable;
import com.proyecto.jirabeta.services.ProyectoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionarProyectoPanel extends JPanel implements Igestionable {

    private ProyectoTableModel proyectoTableModel;
    private List<ProyectoDTO> proyectoList;
    private ProyectoService proyectoService;
    private ProyectoDTO proyectoDTO;
    private JTable tablaEmpleados;
    private JButton btnPersistir;
    private JButton btnVolver;

    public GestionarProyectoPanel(ProyectoTableModel proyectoTableModel, List<ProyectoDTO> proyectoList, ProyectoService proyectoService) {
        this.proyectoTableModel = proyectoTableModel;
        this.proyectoList = proyectoList;
        this.proyectoService = proyectoService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel formularioPanel = new ProyectoFormularioPanelRefactor(this.proyectoTableModel, this.proyectoList
        , this.proyectoService);
        String[] columnas = {"ID", "Nombre", "Fecha de Fin"};
        Object[][] datos = {};  // Inicialmente vacío
        JTable tablaProyectos = new JTable(datos, columnas);
        tablaProyectos.add(new JLabel("Seleccionar Empleados", JLabel.CENTER), BorderLayout.NORTH);
        JScrollPane scrollTabla = new JScrollPane(tablaProyectos);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnVolver = new JButton("Volver");

        btnAgregar.addActionListener(e -> {

        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnVolver);


        JPanel panelContenedor = new JPanel(new BorderLayout(10, 10));
        panelContenedor.add(formularioPanel, BorderLayout.WEST);
        panelContenedor.add(scrollTabla, BorderLayout.CENTER);
        panelContenedor.add(panelBotones, BorderLayout.SOUTH);

        // Agregar al panel principal
        add(panelContenedor, BorderLayout.CENTER);
    }

    @Override
    public void persistir() {

    }

    @Override
    public void volver() {

    }
}

