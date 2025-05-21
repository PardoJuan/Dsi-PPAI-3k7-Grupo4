package com.k7.dsi.ppai.grupo4.interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Clase principal con la interfaz y el botón
public class InterfazGraficaCU37 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interfaz CU 37");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();

        JButton botonCU37 = new JButton("Cerrar Orden de Inspeccion");

        // Acción: abrir nueva pantalla
        botonCU37.addActionListener(e -> {
            NuevaPantalla nueva = new NuevaPantalla();
            nueva.setVisible(true);
        });

        panel.add(botonCU37);
        frame.add(panel);
        frame.setVisible(true);
    }
}

// Nueva ventana con lista seleccionable
class NuevaPantalla extends JFrame {

    public NuevaPantalla() {
        setTitle("Seleccionar elemento");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Lista de elementos
        String[] elementos = { "Opción A", "Opción B", "Opción C", "Opción D" };
        JList<String> lista = new JList<>(elementos);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(lista);

        // Botón para confirmar selección
        JButton botonSeleccionar = new JButton("Seleccionar");

        // Acción del botón
        botonSeleccionar.addActionListener(e -> {
            String seleccion = lista.getSelectedValue();
            if (seleccion != null) {
                JOptionPane.showMessageDialog(this,
                    "Has seleccionado: " + seleccion,
                    "Selección",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Por favor, selecciona un elemento.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(botonSeleccionar, BorderLayout.SOUTH);
        add(panel);
    }
}