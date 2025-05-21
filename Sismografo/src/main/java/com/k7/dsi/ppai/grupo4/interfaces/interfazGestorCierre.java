package com.k7.dsi.ppai.grupo4.interfaces;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import com.k7.dsi.ppai.grupo4.gestor.gestorCierreOrden;
import com.k7.dsi.ppai.grupo4.entidades.*;

public class interfazGestorCierre extends JFrame {
    private static final long serialVersionUID = 1L;

    public void habilitarVentana(sesion sesion, ArrayList<ordenInspeccion> ordenesRealizadas, ArrayList<sismografo> sismografos, ArrayList<motivoTipo> tiposMotivos, ArrayList<estado> estados) {
        // Configuración básica de la ventana
        setTitle("Gestor de Cierre");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));

        // Configuración de GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel titulo = new JLabel("Gestor de Cierre");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        // Botón Aceptar
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAceptar.setPreferredSize(new Dimension(150, 50));
        btnAceptar.setBackground(new Color(100, 150, 255));
        btnAceptar.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(btnAceptar, gbc);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCancelar.setPreferredSize(new Dimension(150, 50));
        btnCancelar.setBackground(new Color(255, 150, 100));
        btnCancelar.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnCancelar, gbc);

        // Acción para el botón Aceptar
        btnAceptar.addActionListener(e -> {
            gestorCierreOrden gestor = new gestorCierreOrden();
            gestor.conocerRI(sesion);
            gestor.buscarOrdenesRealizadas(gestor.getResponsableInspecciones(), ordenesRealizadas, sismografos);
            gestor.ordenarPorFechaHoraFin();
            getContentPane().removeAll();
            revalidate();
            repaint();

            ArrayList<String> listaOrdenes = gestor.mostrarOrdenesRealizadas();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String orden : listaOrdenes) {
                listModel.addElement(orden);
            }
            JList<String> ordenesList = new JList<>(listModel);
            ordenesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            ordenesList.setFont(new Font("Arial", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(ordenesList);

            JPanel nuevaPantalla = new JPanel(new GridBagLayout());
            nuevaPantalla.setBackground(new Color(240, 240, 240));
            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.insets = new Insets(10, 10, 10, 10);
            gbc2.gridx = 0;
            gbc2.gridy = 0;
            gbc2.gridwidth = 2;
            nuevaPantalla.add(new JLabel("Seleccione una orden realizada:"), gbc2);

            gbc2.gridy = 1;
            gbc2.gridwidth = 2;
            gbc2.fill = GridBagConstraints.BOTH;
            gbc2.weightx = 1.0;
            gbc2.weighty = 1.0;
            nuevaPantalla.add(scrollPane, gbc2);

            JButton btnConfirmar = new JButton("Confirmar");
            JButton btnCancelarSeleccion = new JButton("Cancelar");

            gbc2.gridy = 2;
            gbc2.gridwidth = 1;
            gbc2.weightx = 0.5;
            gbc2.weighty = 0;
            gbc2.fill = GridBagConstraints.NONE;
            nuevaPantalla.add(btnConfirmar, gbc2);

            gbc2.gridx = 1;
            nuevaPantalla.add(btnCancelarSeleccion, gbc2);

            setContentPane(nuevaPantalla);
            revalidate();
            repaint();
            btnConfirmar.addActionListener(e1 -> {
                int selectedIndex = ordenesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedOrder = listaOrdenes.get(selectedIndex);
                    JOptionPane.showMessageDialog(this, "Orden seleccionada: " + selectedOrder);
                    JTextArea observacionArea = new JTextArea(5, 30);
                    observacionArea.setLineWrap(true);
                    observacionArea.setWrapStyleWord(true);
                    JScrollPane observacionScroll = new JScrollPane(observacionArea);

                    int result = JOptionPane.showConfirmDialog(
                        this,
                        observacionScroll,
                        "Ingrese la observación",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        String observacion = observacionArea.getText();
                        JOptionPane.showMessageDialog(this, "Observación ingresada:\n" + observacion);
                        gestor.tomarObservacion(observacion);
                        ArrayList<String> motivos = gestor.buscarTipoMotivoFS(tiposMotivos);
                        ArrayList<String> motivosSeleccionados = new ArrayList<>();
                        ArrayList<String> comentariosMotivos = new ArrayList<>();

                        for (String motivo : motivos) {
                            JCheckBox checkBox = new JCheckBox(motivo);
                            JPanel panelMotivo = new JPanel(new BorderLayout());
                            panelMotivo.add(checkBox, BorderLayout.NORTH);

                            JTextArea comentarioArea = new JTextArea(3, 25);
                            comentarioArea.setLineWrap(true);
                            comentarioArea.setWrapStyleWord(true);
                            comentarioArea.setEnabled(false);
                            JScrollPane comentarioScroll = new JScrollPane(comentarioArea);
                            panelMotivo.add(comentarioScroll, BorderLayout.CENTER);

                            checkBox.addActionListener(ev -> comentarioArea.setEnabled(checkBox.isSelected()));

                            int res = JOptionPane.showConfirmDialog(
                                this,
                                panelMotivo,
                                "Seleccione motivo y agregue comentario",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                            );

                            if (res == JOptionPane.OK_OPTION && checkBox.isSelected()) {
                                motivosSeleccionados.add(motivo);
                                comentariosMotivos.add(comentarioArea.getText());
                            }
                        }

                        if (!motivosSeleccionados.isEmpty()) {
                            StringBuilder resumen = new StringBuilder("Motivos seleccionados:\n");
                            for (int i = 0; i < motivosSeleccionados.size(); i++) {
                                resumen.append("- ").append(motivosSeleccionados.get(i)).append(": ")
                                       .append(comentariosMotivos.get(i)).append("\n");
                            }
                            int confirm = JOptionPane.showConfirmDialog(
                                this,
                                resumen.toString() + "\n¿Confirma el cierre con estos motivos?",
                                "Confirmar cierre",
                                JOptionPane.YES_NO_OPTION
                            );
                            if (confirm == JOptionPane.YES_OPTION) {
                                // Aquí puedes llamar a gestor.tomarMotivos(motivosSeleccionados, comentariosMotivos);
                                JOptionPane.showMessageDialog(this, "Cierre confirmado.");
                                gestor.tomarSeleccionTM(motivosSeleccionados);
                                gestor.tomarComentario(comentariosMotivos);
                                if (gestor.validarExisteObservacionYMotivo()) {
                                    // Continúa con el flujo normal si la validación es exitosa
                                    gestor.getFechaHoraActual();
                                    for (estado estado : estados) {
                                        if (gestor.esAmbitoOrdenInspeccion(estado) && gestor.esCerrada(estado)) {
                                            ordenInspeccion ordenSeleccionada = null;
                                            for (ordenInspeccion oi : ordenesRealizadas) {
                                                if (oi.getNumeroOrden().equals(gestor.getNroOrden().get(selectedIndex))) {
                                                    ordenSeleccionada = oi;
                                                    break;
                                                }
                                            }
                                            if (ordenSeleccionada == null) {
                                                JOptionPane.showMessageDialog(this, "No se encontró la orden seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                                                return;
                                            }
                                            gestor.cerrarOrdenInspeccion(estado, ordenSeleccionada);
                                            gestor.actualizarSismografoBaja(estados, ordenSeleccionada);



                                        } else {
                                        }
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "Debe ingresar una observación y al menos un motivo para cerrar la orden.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, seleccione una orden.");
                }
            });
        });
    