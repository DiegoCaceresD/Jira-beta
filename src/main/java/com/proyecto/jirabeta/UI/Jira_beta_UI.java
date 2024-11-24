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
        ProyectoFormularioPanel formularioPanel = new ProyectoFormularioPanel(proyectoList, proyectoTableModel, cardLayout, panelPrincipal);
//        ProyectoFormularioPanelRefactor formularioPanelRefactor = new ProyectoFormularioPanelRefactor(proyectoTableModel, proyectoList,proyectoService);

        ListaProyectosPanel listaProyectosPanel = new ListaProyectosPanel(proyectoList, proyectoTableModel, cardLayout, panelPrincipal, formularioPanel);
        GestionarProyectoPanel gestionarProyectoPanel = new GestionarProyectoPanel(proyectoTableModel, proyectoList, proyectoService);
        panelPrincipal.add(listaProyectosPanel, "ListaProyectos");
        panelPrincipal.add(formularioPanel, "FormularioProyecto");
        panelPrincipal.add(gestionarProyectoPanel, "GestionarProyecto");

        add(panelPrincipal, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    private List<ProyectoDTO> cargarProyectos() {
        List<ProyectoDTO> proyectos = new ArrayList<>();
        ResponseDTO responseDTO = new ProyectoService().listarProyectos();
        if (responseDTO.isSuccess()) {
            proyectos = (List<ProyectoDTO>) responseDTO.getData();
        }
        return proyectos;
    }

}
