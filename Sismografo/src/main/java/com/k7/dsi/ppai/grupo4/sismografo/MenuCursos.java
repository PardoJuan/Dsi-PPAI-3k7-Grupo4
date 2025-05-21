package com.k7.dsi.ppai.grupo4.sismografo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCursos extends JFrame {
    
    public MenuCursos() {
        // Configuración básica de la ventana
        setTitle("Menú de Cursos");
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
        JLabel titulo = new JLabel("Seleccione el tipo de curso:");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);
        
        // Botón Curso Normal
        JButton btnNormal = new JButton("Curso Normal");
        btnNormal.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNormal.setPreferredSize(new Dimension(200, 50));
        btnNormal.setBackground(new Color(100, 150, 255));
        btnNormal.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(btnNormal, gbc);
        
        // Botón Curso Alternativo
        JButton btnAlternativo = new JButton("Curso Alternativo");
        btnAlternativo.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAlternativo.setPreferredSize(new Dimension(200, 50));
        btnAlternativo.setBackground(new Color(255, 150, 100));
        btnAlternativo.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnAlternativo, gbc);
        
        // Acción para el botón Curso Normal
        btnNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuCursos.this, 
                    "Ha seleccionado el Curso Normal", 
                    "Curso Normal", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Aquí puedes agregar la lógica para el curso normal
            }
        });
        
        // Acción para el botón Curso Alternativo
        btnAlternativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuCursos.this, 
                    "Ha seleccionado el Curso Alternativo", 
                    "Curso Alternativo", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Aquí puedes agregar la lógica para el curso alternativo
            }
        });
        
        // Agregar panel a la ventana
        add(panel);
    }
    
    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de eventos
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuCursos().setVisible(true);
            }
        });
    }
}