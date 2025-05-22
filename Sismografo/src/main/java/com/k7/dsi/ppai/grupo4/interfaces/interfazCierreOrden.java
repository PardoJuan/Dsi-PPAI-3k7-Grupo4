package com.k7.dsi.ppai.grupo4.interfaces;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.k7.dsi.ppai.grupo4.gestor.gestorCierreOrden;




public class interfazCierreOrden {
    public interfazCierreOrden() {
       
        // Crear la ventana principal
        JFrame frame = new JFrame("Cierre de Orden");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());
        JButton btnCierreOrden = new JButton("Cierre Orden");
        frame.add(btnCierreOrden);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btnCierreOrden.addActionListener(e -> {
            gestorCierreOrden gestor = new gestorCierreOrden();
            frame.dispose();
            mostrarPantallaGrilla(gestor);
        });
    }
     private void mostrarPantallaGrilla(gestorCierreOrden gestor) {
        JFrame nuevaVentana = new JFrame("Seleccionar Orden");
        nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaVentana.setSize(400, 300);

        // Datos de ejemplo para la grilla
        String[] columnas = {"N° Orden", "Fecha Finalizacion", "Nombre Estacion", "ID Sismografo"};
        String[][] datos = new String[gestor.getInpeccionesOrdenada().size()][columnas.length];
        for (int i = 0; i < gestor.getInpeccionesOrdenada().size(); i++) {
            ArrayList<Object> fila = gestor.getInpeccionesOrdenada().get(i);
            for (int j = 0; j < columnas.length; j++) {
                datos[i][j] = fila.get(j).toString();
            }
        }
        // Crear la tabla
        JTable tabla = new JTable(datos, columnas);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tabla);
        nuevaVentana.add(scrollPane, BorderLayout.CENTER);

        // Botón para confirmar selección
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                String idSeleccionado = tabla.getValueAt(filaSeleccionada, 0).toString();
                String descripcion = tabla.getValueAt(filaSeleccionada, 1).toString();
                JOptionPane.showMessageDialog(nuevaVentana, "Seleccionaste: " + idSeleccionado + " - " + descripcion);
                gestor.tomarSelecionOrden(tabla.getValueAt(filaSeleccionada, 0).toString(), tabla.getValueAt(filaSeleccionada, 1).toString(), tabla.getValueAt(filaSeleccionada, 2).toString(), tabla.getValueAt(filaSeleccionada, 3).toString());
                String comentario = JOptionPane.showInputDialog(nuevaVentana, "Ingrese un comentario para el cierre:");
                if (comentario != null && !comentario.trim().isEmpty()) {
            // Llamar al gestor con todos los datos
                    gestor.tomarComentario(comentario.toString());

                    JOptionPane.showMessageDialog(nuevaVentana, "Orden cerrada con comentario.");
                    nuevaVentana.dispose();

                    mostrarVentanaAveriaUnaPorUna(gestor, gestor.getMotivos(), 0);
                    


                } else {
                    JOptionPane.showMessageDialog(nuevaVentana, "Debe ingresar un comentario.");
                    }
            } else {
                JOptionPane.showMessageDialog(nuevaVentana, "Por favor, selecciona una fila.");
            }
        });

        nuevaVentana.add(btnSeleccionar, BorderLayout.SOUTH);
        nuevaVentana.setLocationRelativeTo(null);
        nuevaVentana.setVisible(true);


    }
    private void mostrarVentanaAveriaUnaPorUna(gestorCierreOrden gestor, ArrayList<String> motivos, int indice) {
    if (indice >= motivos.size()) {
        JOptionPane.showMessageDialog(null, "Todas las averías han sido procesadas.");
        if (gestor.validarExisteObservacionYMotivo()) {
            JOptionPane.showMessageDialog(null, "Se han registrado todos los motivos.");
            gestor.continuarCierreOrden();

        } else {
            JOptionPane.showMessageDialog(null, "No se han registrado motivos.");
        }
        return;
    }

    String motivoActual = motivos.get(indice);

    JFrame frame = new JFrame("Avería: " + motivoActual);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(400, 250);
    frame.setLayout(new BorderLayout());

    JLabel lblTitulo = new JLabel("Tipo de avería: " + motivoActual);
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

    JTextArea comentarioArea = new JTextArea(5, 30);
    JScrollPane scrollComentario = new JScrollPane(comentarioArea);
    scrollComentario.setBorder(BorderFactory.createTitledBorder("Comentario"));

    // Panel de botones
    JPanel panelBotones = new JPanel(new FlowLayout());

    JButton btnGuardar = new JButton("Guardar Comentario");
    btnGuardar.addActionListener((ActionEvent e) -> {
        String comentario = comentarioArea.getText().trim();
        if (comentario.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un comentario o use 'Saltar'.");
            return;
        }

         // registrar el comentario
        gestor.tomarSeleccionTM(motivoActual);
        gestor.tomarComentario(comentario);
        frame.dispose();
        mostrarVentanaAveriaUnaPorUna(gestor, motivos, indice + 1);
    });

    JButton btnSaltar = new JButton("Saltar");
    btnSaltar.addActionListener((ActionEvent e) -> {
         // o simplemente omitir esto
        frame.dispose();
        mostrarVentanaAveriaUnaPorUna(gestor, motivos, indice + 1);
    });

    panelBotones.add(btnGuardar);
    panelBotones.add(btnSaltar);

    frame.add(lblTitulo, BorderLayout.NORTH);
    frame.add(scrollComentario, BorderLayout.CENTER);
    frame.add(panelBotones, BorderLayout.SOUTH);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new interfazCierreOrden());
    }
}