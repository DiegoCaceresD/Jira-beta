package com.proyecto.jirabeta.UI;

import com.proyecto.jirabeta.DTOs.ProyectoDTO;
import com.proyecto.jirabeta.DTOs.ResponseDTO;
import com.proyecto.jirabeta.UI.Proyecto.ListaProyectosPanel;
import com.proyecto.jirabeta.UI.Proyecto.ProyectoFormularioPanel;
import com.proyecto.jirabeta.UI.Proyecto.ProyectoTableModel;
import com.proyecto.jirabeta.services.ProyectoService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Jira_beta_UI extends JFrame {
    private JTable proyectoTable;
    private ProyectoTableModel proyectoTableModel;
    private List<ProyectoDTO> proyectoList;
    private ProyectoService proyectoService;
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    public Jira_beta_UI() {
        proyectoList = cargarProyectos();
        proyectoTableModel = new ProyectoTableModel(proyectoList);
        proyectoService = new ProyectoService();
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        setTitle("Jira-beta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Agregar los paneles a la interfaz
        ListaProyectosPanel listaProyectosPanel = new ListaProyectosPanel(proyectoList, proyectoTableModel, cardLayout, panelPrincipal);
        panelPrincipal.add(listaProyectosPanel, "ListaProyectos");
        ProyectoFormularioPanel formularioPanel = new ProyectoFormularioPanel();
        JPanel formularioProyectoPanel = formularioPanel.createFormularioPanel(panelPrincipal, proyectoList, proyectoTableModel, cardLayout);
        panelPrincipal.add(formularioProyectoPanel, "FormularioProyecto");

        add(panelPrincipal, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public List<ProyectoDTO> cargarProyectos() {
        List<ProyectoDTO> proyectos = new ArrayList<>();
        ResponseDTO responseDTO = new ProyectoService().listarProyectos();
        if (responseDTO.isSuccess()) {
            proyectos = (List<ProyectoDTO>) responseDTO.getData();
        }
        return proyectos;
    }

}
