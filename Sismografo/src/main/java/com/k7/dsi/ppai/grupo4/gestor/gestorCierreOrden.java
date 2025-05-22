package com.k7.dsi.ppai.grupo4.gestor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import com.k7.dsi.ppai.grupo4.entidades.cambioEstado;
import com.k7.dsi.ppai.grupo4.entidades.empleado;
import com.k7.dsi.ppai.grupo4.entidades.estacionSismologica;
import com.k7.dsi.ppai.grupo4.entidades.estado;
import com.k7.dsi.ppai.grupo4.entidades.motivoTipo;
import com.k7.dsi.ppai.grupo4.entidades.ordenInspeccion;
import com.k7.dsi.ppai.grupo4.entidades.rol;
import com.k7.dsi.ppai.grupo4.entidades.sesion;
import com.k7.dsi.ppai.grupo4.entidades.sismografo;
import com.k7.dsi.ppai.grupo4.entidades.usuario;


public class gestorCierreOrden {
    private empleado responsableInspecciones;
    private ArrayList<ArrayList<Object>> inpeccionesOrdenada = new ArrayList<ArrayList<Object>>();
    private ArrayList<String> ordenSelecionada = new ArrayList<String>();
    private String comentario;
    private ArrayList<String> motivos = new ArrayList<String>();
    private ArrayList<String> motivosSeleccionados = new ArrayList<String>();
    private ArrayList<String> comentariosMotivos = new ArrayList<String>();
    
    public gestorCierreOrden(){
        empleado empleadoPrueba = new empleado("Juan", "Perez", "juan@gmail.com", "123456789", new rol("Responsable de Inspeccion", "descripcion"));
        estacionSismologica estacionSismologicaPrueba = new estacionSismologica("1", "", "2023-10-01 10:00:00", 1, 1, "Estacion cordoba", 1);
        sesion sesion = new sesion("2023-10-01 10:00:00", null, new usuario("admin", "admin", empleadoPrueba));

        ArrayList<ordenInspeccion> ordenes = new ArrayList<ordenInspeccion>();
            ordenes.add(new ordenInspeccion(null, "2023-10-01 12:00:00", "2023-10-01 10:00:00", 1, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"), empleadoPrueba, estacionSismologicaPrueba));
            ordenes.add(new ordenInspeccion(null, "2023-10-01 12:00:00", "2023-10-01 10:00:00", 2, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"),new empleado("Jose", "Perez", "jose@gmail.com", "123456789", new rol("Responsable de Inspeccion", "descripcion")), new estacionSismologica("2", "", "2023-10-01 10:00:00", 2, 2, "Estacion Mendoza", 2)));
            ordenes.add(new ordenInspeccion(null, "2023-12-01 12:00:00", "2023-10-01 10:00:00", 3, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"), empleadoPrueba, estacionSismologicaPrueba));
            ordenes.add(new ordenInspeccion(null, "2023-09-01 12:00:00", "2023-10-01 10:00:00", 4, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"), empleadoPrueba, estacionSismologicaPrueba));

        ArrayList<motivoTipo> motivosTipo = new ArrayList<motivoTipo>();
            motivosTipo.add(new motivoTipo("Averia por vibracion"));
            motivosTipo.add(new motivoTipo("Desgaste de componente"));
            motivosTipo.add(new motivoTipo("Fallo en el sistema de registro"));
            motivosTipo.add(new motivoTipo("Bandalismo"));
            motivosTipo.add(new motivoTipo("Fallo en fuente de alimentacion"));
            motivosTipo.add(new motivoTipo("Otro (Tendra que aclarar...)"));
            
            
        ArrayList<sismografo> sismografos = new ArrayList<sismografo>();
            sismografos.add(new sismografo("2023-10-01", "1", 1, new estado("Sismografo", "En operacion"), new cambioEstado("2023-10-01", "2023-10-01", new estado("Sismografo", "En operacion")), estacionSismologicaPrueba));
        ArrayList<estado> estados = new ArrayList<estado>();
            estados.add(new estado("Orden de Inspeccion", "Cerrada"));
            estados.add(new estado("Orden de Inspeccion", "Realizada"));
            estados.add(new estado("Sismografo", "En operacion"));
            estados.add(new estado("Sismografo", "Fuera de servicio"));
        this.conocerRI(sesion);
        ArrayList<ArrayList<Object>> inpecciones = this.buscarOrdenesRealizadas(ordenes, sismografos);
        inpecciones = ordenarPorFechaFin(inpecciones);
        this.inpeccionesOrdenada = inpecciones;
        
        System.out.println(inpecciones);
        ArrayList<String> motivos = buscarTipoMotivoFS(motivosTipo);
        this.motivos = motivos;
        System.out.println(motivos);
        
    }
    
    public void conocerRI(sesion sesion) {
        this.responsableInspecciones = sesion.conocerRI().getRIlogeado();
    }

    public ArrayList<ArrayList<Object>> buscarOrdenesRealizadas(ArrayList<ordenInspeccion>ordenes, ArrayList<sismografo> sismografos) {
        ArrayList<ArrayList<Object>> inspecciones = new ArrayList<>();
        for (ordenInspeccion orden : ordenes) {
            if (orden.validarEmpleadoLog(this.responsableInspecciones) && orden.estaRealizada()){
                ArrayList<Object> datosInspeccion = new ArrayList<>();
                datosInspeccion.add(orden.getNumeroOrden());
                datosInspeccion.add(orden.getFechaFinalizacion());
                ArrayList<Object> datosEstacion = orden.buscarEstacionSismologica(sismografos);
                datosInspeccion.add(datosEstacion.get(0));
                datosInspeccion.add(datosEstacion.get(1));
                inspecciones.add(datosInspeccion);
            }
        }

        return inspecciones; 
        
    }

    public ArrayList<ArrayList<Object>> ordenarPorFechaFin(ArrayList<ArrayList<Object>> inspecciones) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        inspecciones.sort(new Comparator<ArrayList<Object>>() {
            @Override
            public int compare(ArrayList<Object> o1, ArrayList<Object> o2) {
                // Extraemos y parseamos la fecha del segundo elemento (índice 1)
                LocalDateTime fecha1 = LocalDateTime.parse(o1.get(1).toString(), formatter);
                LocalDateTime fecha2 = LocalDateTime.parse(o2.get(1).toString(), formatter);
                return fecha1.compareTo(fecha2);
            }
        });
        return inspecciones;
    }
    public ArrayList<ArrayList<Object>> getInpeccionesOrdenada() {
        return inpeccionesOrdenada;
    }

    public void tomarSelecionOrden(String idOrden, String fechaFin, String nombreEstacion, String idSismografo) {
        // Implementar la lógica para tomar la selección de la orden
        this.ordenSelecionada.add(idOrden);
        this.ordenSelecionada.add(fechaFin);
        this.ordenSelecionada.add(nombreEstacion);
        this.ordenSelecionada.add(idSismografo);
        System.out.println("Orden seleccionada: " + idOrden + ", Fecha Fin: " + fechaFin + ", Nombre Estacion: " + nombreEstacion + ", ID Sismografo: " + idSismografo);

    }
    public void tomarComentario(String comentario){
        this.comentario = comentario;
        // Implementar la lógica para tomar el comentario
        System.out.println("Comentario: " + comentario);
    }


    public ArrayList<String> buscarTipoMotivoFS(ArrayList<motivoTipo> motivos){
        ArrayList<String> nombresMT = new ArrayList<>();
        for ( motivoTipo motivo: motivos) {
            nombresMT.add(motivo.getDescripcion());
        }
        return nombresMT; 
    }
    public ArrayList<String> getMotivos() {
        return motivos;
    };

    public void tomarSeleccionTM(String motivoSeleccionado) {
        this.motivosSeleccionados.add(motivoSeleccionado);
        // Implementar la lógica para tomar la selección del motivo
        System.out.println("Motivo seleccionado: " + motivoSeleccionado);
    }
    public void tomarComentarioMotivo(String comentarioMotivo) {
        this.comentariosMotivos.add(comentarioMotivo);
        // Implementar la lógica para tomar el comentario del motivo
        System.out.println("Comentario del motivo: " + comentarioMotivo);
    }

    public boolean validarExisteObservacionYMotivo() {
        System.out.println("Comentario: " + this.comentario);
        System.out.println("Motivos seleccionados: " + this.motivosSeleccionados);
        // Implementar la lógica para validar si se seleccionaron motivos
        if (this.comentario != null && !this.comentario.trim().isEmpty() && !this.motivosSeleccionados.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void actualizarOrden(ArrayList<ordenInspeccion> ordenes, ArrayList<estado> estados){
        for (ordenInspeccion orden : ordenes){
            if (orden.getNumeroOrden().equals(Integer.valueOf(this.ordenSelecionada.get(0).toString()))){
                orden.setObservacionCierre(this.comentario);
                orden.setFechaHoraCierre(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                
                for (estado estado : estados){
                    if (estado.getAmbito().equals("Orden de Inspeccion") && estado.getNombreEstado().equals("Cerrada")){
                        orden.setEstado(estado);
                        System.out.println("Orden actualizada: " + orden.getNumeroOrden());
                        System.out.println("Fecha seleccionada: " + orden.getFechaFinalizacion());
                    }
                }
                
                
            }
        }
        
    }
    public void continuarCierreOrden(){
        empleado empleadoPrueba = new empleado("Juan", "Perez", "juan@gmail.com", "123456789", new rol("Responsable de Inspeccion", "descripcion"));
        estacionSismologica estacionSismologicaPrueba = new estacionSismologica("1", "", "2023-10-01 10:00:00", 1, 1, "Estacion cordoba", 1);
        sesion sesion = new sesion("2023-10-01 10:00:00", null, new usuario("admin", "admin", empleadoPrueba));

        ArrayList<ordenInspeccion> ordenes = new ArrayList<ordenInspeccion>();
            ordenes.add(new ordenInspeccion(null, "2023-10-01 12:00:00", "2023-10-01 10:00:00", 1, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"), empleadoPrueba, estacionSismologicaPrueba));
            ordenes.add(new ordenInspeccion(null, "2023-10-01 12:00:00", "2023-10-01 10:00:00", 2, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"),new empleado("Jose", "Perez", "jose@gmail.com", "123456789", new rol("Responsable de Inspeccion", "descripcion")), new estacionSismologica("2", "", "2023-10-01 10:00:00", 2, 2, "Estacion Mendoza", 2)));
            ordenes.add(new ordenInspeccion(null, "2023-12-01 12:00:00", "2023-10-01 10:00:00", 3, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"), empleadoPrueba, estacionSismologicaPrueba));
            ordenes.add(new ordenInspeccion(null, "2023-09-01 12:00:00", "2023-10-01 10:00:00", 4, "Observacion de cierre", new estado("Orden de inspeccion", "Realizada"), empleadoPrueba, estacionSismologicaPrueba));

        ArrayList<motivoTipo> motivosTipo = new ArrayList<motivoTipo>();
            motivosTipo.add(new motivoTipo("Averia por vibracion"));
            motivosTipo.add(new motivoTipo("Desgaste de componente"));
            motivosTipo.add(new motivoTipo("Fallo en el sistema de registro"));
            motivosTipo.add(new motivoTipo("Bandalismo"));
            motivosTipo.add(new motivoTipo("Fallo en fuente de alimentacion"));
            motivosTipo.add(new motivoTipo("Otro (Tendra que aclarar...)"));
            
        ArrayList<rol> roles = new ArrayList<rol>();
            roles.add(new rol("Responsable de Inspeccion", "descripcion"));
            roles.add(new rol("Responsable de Reparacion", "descripcion"));
            roles.add(new rol("Tecnico", "descripcion"));
            roles.add(new rol("Tecnico de Campo", "descripcion"));
            roles.add(new rol("Tecnico de Mantenimiento", "descripcion"));
            roles.add(new rol("Tecnico de Soporte", "descripcion"));
        ArrayList<empleado> empleados = new ArrayList<empleado>();
            empleados.add(new empleado("Jose", "Jose", "josejose@gmail.com", "123456789", roles.get(1)));
            empleados.add(new empleado("Jose", "Mario", "joseMario@gmail.com", "123456789", roles.get(1)));
            empleados.add(new empleado("Josefa", "Josefa", "josefajosefa@gmail.com", "123456789", roles.get(4)));
            
        ArrayList<sismografo> sismografos = new ArrayList<sismografo>();
            sismografos.add(new sismografo("2023-10-01", "1", 1, new estado("Sismografo", "En operacion"), new cambioEstado("2023-10-01", "2023-10-01", new estado("Sismografo", "En operacion")), estacionSismologicaPrueba));
        ArrayList<estado> estados = new ArrayList<estado>();
            estados.add(new estado("Orden de Inspeccion", "Cerrada"));
            estados.add(new estado("Orden de Inspeccion", "Realizada"));
            estados.add(new estado("Sismografo", "En operacion"));
            estados.add(new estado("Sismografo", "Fuera de servicio"));
        this.actualizarOrden(ordenes, estados);
        this.actualizarSismografoBaja(sismografos, estados);
        String comentarioEnviar = "Sismografo n° "+this.ordenSelecionada.get(3) + "estado fuera de servicio por: " + this.motivosSeleccionados + " a la hora "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.enviarMail(empleados, comentarioEnviar);
        this.publicarEnMonitores(comentarioEnviar);


    }
    public void actualizarSismografoBaja(ArrayList<sismografo> sismografos, ArrayList<estado> estados){
        for (sismografo sismografo : sismografos){
            if (sismografo.getIdSismografo().equals(this.ordenSelecionada.get(3))){
                for (estado estado : estados){
                    if (estado.getAmbito().equals("Sismografo") && estado.getNombreEstado().equals("Fuera de servicio")){
                        // cambiar estado sismografo
                        // sismografo.setEstado(estado);
                        // sismografo.setCambioEstado(new cambioEstado(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), null, estado, new motivoFueraServicio(this.comentariosMotivos, this.motivosSeleccionados)));
                        System.out.println("Sismografo actualizado: " + sismografo.getIdSismografo());
                        System.out.println("Fecha seleccionada: " + sismografo.getCambioEstado().getFechaHoraFin());
                    }
                }
            }
        }
    }
    public void enviarMail(ArrayList<empleado> empleados, String comentario){
        // Implementar la lógica para enviar el mail
        for (empleado empleado : empleados) {
            if (empleado.getRol().getNombre().equals("Responsable de Reparacion")) {
                System.out.println("Enviando mail a: " + empleado.getMail() + " con comentario: " + comentario);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    javax.swing.JFrame frame = new javax.swing.JFrame("Correo Electrónico");
                    frame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(400, 350);

                    javax.swing.JPanel panel = new javax.swing.JPanel();
                    panel.setLayout(new java.awt.BorderLayout(10, 10));

                    javax.swing.JLabel asuntoLabel = new javax.swing.JLabel("Asunto: Aviso de avería sismógrafo");
                    asuntoLabel.setFont(asuntoLabel.getFont().deriveFont(java.awt.Font.BOLD, 14f));
                    panel.add(asuntoLabel, java.awt.BorderLayout.NORTH);

                    javax.swing.JPanel infoPanel = new javax.swing.JPanel();
                    infoPanel.setLayout(new java.awt.GridLayout(2, 1));
                    javax.swing.JLabel paraLabel = new javax.swing.JLabel("Para: " + empleado.getMail());
                    javax.swing.JLabel deLabel = new javax.swing.JLabel("De: alertasAverias@estaciones.com");
                    infoPanel.add(paraLabel);
                    infoPanel.add(deLabel);
                    panel.add(infoPanel, java.awt.BorderLayout.WEST);

                    javax.swing.JTextArea mensajeArea = new javax.swing.JTextArea(comentario);
                    mensajeArea.setLineWrap(true);
                    mensajeArea.setWrapStyleWord(true);
                    mensajeArea.setEditable(false);
                    javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(mensajeArea);
                    panel.add(scroll, java.awt.BorderLayout.CENTER);

                    frame.getContentPane().add(panel);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                });
            }
            
        }
    }
    public void publicarEnMonitores(String comentario){
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                comentario,
                "Alerta - Aviso de avería sismógrafo",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
        });
    }


    //public ArrayList<ArrayList> ordenarPorFechaFin(ArrayList<ArrayList> ordenes){};
};




