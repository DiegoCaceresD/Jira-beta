package com.proyecto.jirabeta.UI.Classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public abstract class FormularioPanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(FormularioPanel.class);
    protected Map<String, JComponent> fields;

    public FormularioPanel() {
        this.fields = this.createFields();
        this.createFormularioPanel();
    }

    JPanel createFormularioPanel() {
        setLayout(new BorderLayout(10,10));
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));

        //creo los campos de los formularios
        this.fields.forEach((label, component) -> {
            panelFormulario.add(new JLabel(label));
            panelFormulario.add(component);
        });

        //creo los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnLimpiar);

        btnLimpiar.addActionListener(e ->{
            limpiarFormulario();
        });

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setPreferredSize(new Dimension(300,0));
        setMaximumSize(new Dimension(100, 0));
        return panelFormulario;
    }


    protected abstract void cargarDatos(Object data);

    protected abstract Map<String, JComponent> createFields();

    protected void limpiarFormulario() {
        this.fields.forEach((label, component) -> {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JComboBox) {
                ((JComboBox<?>) component).setSelectedIndex(0);
            } else {
                logger.warn("No se pudo limpiar el campo {}", label);
            }
        });
    }
}
