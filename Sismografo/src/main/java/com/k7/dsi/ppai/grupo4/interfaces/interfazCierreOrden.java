package com.k7.dsi.ppai.grupo4.interfaces;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.k7.dsi.ppai.grupo4.entidades.cambioEstado;
import com.k7.dsi.ppai.grupo4.entidades.empleado;
import com.k7.dsi.ppai.grupo4.entidades.estacionSismologica;
import com.k7.dsi.ppai.grupo4.entidades.estado;
import com.k7.dsi.ppai.grupo4.entidades.ordenInspeccion;
import com.k7.dsi.ppai.grupo4.entidades.rol;
import com.k7.dsi.ppai.grupo4.entidades.sesion;
import com.k7.dsi.ppai.grupo4.entidades.sismografo;
import com.k7.dsi.ppai.grupo4.entidades.usuario;
import com.k7.dsi.ppai.grupo4.gestor.gestorCierreOrden;




public class interfazCierreOrden {
    public interfazCierreOrden() {
        sesion sesion = new sesion("2023-10-01 10:00:00", "2023-10-01 12:00:00", new usuario("admin", "admin", new empleado("Juan", "Perez", "juan@gmail.com", "123456789", new rol("Responsable de Inspeccion", "descripcion"))));
        ArrayList<ordenInspeccion> ordenes = new ArrayList<ordenInspeccion>();
        ordenes.add(new ordenInspeccion("2023-10-01 10:00:00", "2023-10-01 12:00:00", "2023-10-01 10:00:00", 1, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"),new empleado("Juan", "Perez", "juan@gmail.com", "123456789", new rol("Responsable de Inspeccion", "descripcion")), new estacionSismologica("1", "", "2023-10-01 10:00:00", 1, 1, "Estacion cordoba", 1)));
        ordenes.add(new ordenInspeccion("2023-10-01 10:00:00", "2023-10-01 12:00:00", "2023-10-01 10:00:00", 1, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"),new empleado("Jose", "Perez", "jose@gmail.com", "123456789", new rol("Responsable de Inspeccion", "descripcion")), new estacionSismologica("2", "", "2023-10-01 10:00:00", 2, 2, "Estacion Mendoza", 2)));

        ArrayList<sismografo> sismografos = new ArrayList<sismografo>();
        sismografos.add(new sismografo("2023-10-01", "Sismografo 1", 1, new estado("Sismografo", "En operacion"), new cambioEstado("2023-10-01", "2023-10-01", new estado("Sismografo", "En operacion")),new estacionSismologica("1", "", "2023-10-01 10:00:00", 1, 1, "Estacion cordoba", 1)));

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
            System.out.println("gestor creado.");
            gestor.conocerRI(sesion);
            System.out.println(sesion.getFechaHoraFin());
            gestor.buscarOrdenesRealizadas(sesion.getUsuario(), ordenes, sismografos);
            System.out.println("buscarOrdenesRealizadas");
            gestor.ordenarPorFechaHoraFin();
            ArrayList<String> ordenesRealizadas = gestor.mostrarOrdenesRealizadas();
            
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new interfazCierreOrden());
    }
}