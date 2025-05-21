package com.k7.dsi.ppai.grupo4.gestor;
import java.util.ArrayList;

import com.k7.dsi.ppai.grupo4.entidades.cambioEstado;
import com.k7.dsi.ppai.grupo4.entidades.empleado;
import com.k7.dsi.ppai.grupo4.entidades.estado;
import com.k7.dsi.ppai.grupo4.entidades.motivoTipo;
import com.k7.dsi.ppai.grupo4.entidades.ordenInspeccion;
import com.k7.dsi.ppai.grupo4.entidades.sesion;
import com.k7.dsi.ppai.grupo4.entidades.sismografo;
import com.k7.dsi.ppai.grupo4.entidades.usuario;
public class gestorCierreOrden {
    private usuario responsableInspecciones;
    private ArrayList<Integer> nroOrden = new ArrayList<>();
    private ArrayList<String> fechaHoraFinalizacion = new ArrayList<>();
    private ArrayList<String> nombreEstacion = new ArrayList<>();
    private ArrayList<String> identificadorSismografo = new ArrayList<>();
    private String observacion;
    private ArrayList<String> motivosSeleccionados = new ArrayList<>();
    private ArrayList<String> comentariosTomados = new ArrayList<>();
    private String fechaHoraActual;
    private ArrayList<String> mailResponsablesReparaciones = new ArrayList<>();


    public void conocerRI(sesion sesion) {
        this.responsableInspecciones = sesion.conocerRI().getRIlogeado();
        System.out.println("1");

    }

    public void buscarOrdenesRealizadas(usuario responsableInspecciones , ArrayList<ordenInspeccion> ordenesRealizadas, ArrayList<sismografo> sismografos) {
        for (ordenInspeccion orden : ordenesRealizadas) {
            if (this.validarEmpleadoLog(orden.getEmpleado())) {
                System.out.println("2");
                if(this.estaRealizada(orden)){
                    System.out.println("3");
                    this.nroOrden.add(orden.getNumeroOrden());
                    this.fechaHoraFinalizacion.add(orden.getFechaCierre());
                    this.buscarEstacionSismonologica(orden, sismografos);
                }
            }     
        }
    
    }

    public boolean  validarEmpleadoLog(empleado empleadoOrden) {
        if (empleadoOrden.equals(this.responsableInspecciones.getEmpleado())) {
            return true;
        } else {
            return false;
        }

    }
    public boolean estaRealizada(ordenInspeccion orden) {
        if (orden.getEstado().getNombreEstado().equals("Realizada")) {
            return true;
        } else {
            return false;
        }
    }
    public void buscarEstacionSismonologica(ordenInspeccion orden, ArrayList<sismografo> sismografos) {
    this.nombreEstacion.add(orden.getNombre());
    boolean encontrado = false;
    for (sismografo s : sismografos) {
        if (s.getEstacionSismologica().equals(orden.getEstacionSismologica())) {
            this.identificadorSismografo.add(orden.getEstacionSismologica().getIdentificadorSismografo(s));
            encontrado = true;
            break;
        }
    }
    if (!encontrado) {
        this.identificadorSismografo.add("No se encontró el sismógrafo");
    }
}
    public usuario getResponsableInspecciones() {
        return responsableInspecciones;
    }
    public void ordenarPorFechaHoraFin(){
        if (this.fechaHoraFinalizacion.size()>0){
            if (this.fechaHoraFinalizacion.size() == this.nroOrden.size() && this.fechaHoraFinalizacion.size() == this.nombreEstacion.size() && this.fechaHoraFinalizacion.size() == this.identificadorSismografo.size()){
                ArrayList<String> fechas = new ArrayList<>(this.fechaHoraFinalizacion);
                ArrayList<Integer> numeros = new ArrayList<>(this.nroOrden);
                ArrayList<String> nombres = new ArrayList<>(this.nombreEstacion);
                ArrayList<String> identificadores = new ArrayList<>(this.identificadorSismografo);

                ArrayList<Integer> indices = new ArrayList<>();
                for (int i = 0; i < fechas.size(); i++) {
                    indices.add(i);
                }

                indices.sort((i1, i2) -> fechas.get(i1).compareTo(fechas.get(i2)));

                ArrayList<String> fechasOrdenadas = new ArrayList<>();
                ArrayList<Integer> numerosOrdenados = new ArrayList<>();
                ArrayList<String> nombresOrdenados = new ArrayList<>();
                ArrayList<String> identificadoresOrdenados = new ArrayList<>();

                for (int idx : indices) {
                    fechasOrdenadas.add(fechas.get(idx));
                    numerosOrdenados.add(numeros.get(idx));
                    nombresOrdenados.add(nombres.get(idx));
                    identificadoresOrdenados.add(identificadores.get(idx));
                }

                this.fechaHoraFinalizacion = fechasOrdenadas;
                this.nroOrden = numerosOrdenados;
                this.nombreEstacion = nombresOrdenados;
                this.identificadorSismografo = identificadoresOrdenados;
        }
        } else {
            System.out.println("No hay fechas para ordenar");
        }
    }
    public ArrayList<String> mostrarOrdenesRealizadas() {
        ArrayList<String> ordenes = new ArrayList<>();

        for (int i = 0; i < this.fechaHoraFinalizacion.size(); i++) {
            String orden = "Orden N°: " + this.nroOrden.get(i) + ", Fecha y Hora de Finalización: " + this.fechaHoraFinalizacion.get(i) + ", Nombre de Estación: " + this.nombreEstacion.get(i) + ", Identificador Sismógrafo: " + this.identificadorSismografo.get(i);
            ordenes.add(orden);
        }
        return ordenes;
    }
    public void tomarObservacion(String observacion) {
        this.observacion = observacion;
    }
    public ArrayList<String> buscarTipoMotivoFS(ArrayList<motivoTipo> tiposMotivos) {
        ArrayList<String> motivos = new ArrayList<>();
        for (motivoTipo motivo : tiposMotivos) {
            motivos.add(motivo.getDescripcion());
        }
        return motivos;
    }

    public void tomarSeleccionTM(ArrayList<String> motivosSeleccionados) {
        this.motivosSeleccionados = motivosSeleccionados;        
    }
    public void tomarComentario(ArrayList<String> comentariosTomados) {
        this.comentariosTomados = comentariosTomados;
    }
    public boolean validarExisteObservacionYMotivo(){
        if (this.observacion != null && this.motivosSeleccionados != null && this.comentariosTomados != null) {
            return true;
        } else {
            return false;
        }
    }

    public void getFechaHoraActual(){
        this.fechaHoraActual = java.time.LocalDateTime.now().toString();
    }
    public boolean esAmbitoOrdenInspeccion(estado estado){
        if (estado.getAmbito().equals("Orden Inspección")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean esCerrada(estado estado){
        if (estado.getNombreEstado().equals("Cerrada")) {
            return true;
        } else {
            return false;
        } 
    }
    public void cerrarOrdenInspeccion(estado estado ,ordenInspeccion ordenInspeccion){
        ordenInspeccion.setEstado(estado);
        ordenInspeccion.setFechaHoraCierre(this.fechaHoraActual);

    }

    
    public ArrayList<Integer> getNroOrden() {
        return nroOrden;        
    }
    public void actualizarSismografoBaja(ArrayList<estado> estado, ordenInspeccion ordenInspeccion, ArrayList<sismografo> sismografos, ArrayList<cambioEstado> cambiosEstado) {
        estado estadoSelecionado = this.buscarEstadoParaAsignar(estado);
        this.getFechaHoraActual();
        this.actualizarSismografoFS(ordenInspeccion, estadoSelecionado, sismografos, cambiosEstado);

    }

    public estado buscarEstadoParaAsignar(ArrayList<estado> estado) {
        for (estado est : estado) {
            if (this.esAmbitoSismografo(est) && this.esFueraDeServicio(est)) {
                return est;
            }
        }
        return null;
    }
    public boolean esAmbitoSismografo(estado estado){
        if (estado.getAmbito().equals("Sismografo")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean esFueraDeServicio(estado estado){
        if (estado.getNombreEstado().equals("Fuera de Servicio")) {
            return true;
        } else {
            return false;
        } 
    }
    public void actualizarSismografoFS(ordenInspeccion ordenInspeccion, estado estado, ArrayList<sismografo> sismografos, ArrayList<cambioEstado> cambiosEstado) {
        ordenInspeccion.actualizarSismografoFS( sismografos,  cambiosEstado, this.fechaHoraActual);
    }
    public void obtenerMailResponsablesReparaciones(ArrayList<empleado> empleados) {
        for (empleado empleado : empleados) {
            if (empleado.esResponsableDeReparacion()) {
                this.obtenerMail(empleado);
                }
            }
        }
    public void obtenerMail(empleado empleado){
            this.mailResponsablesReparaciones.add(empleado.obtenerMail());
        }

    public void publicarEnMonitores() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("⚠️ CCRS - Notificación de Cambio de Estado ⚠️\n\n");
        mensaje.append("Sismógrafo: ").append(this.identificadorSismografo).append("\n");
        mensaje.append("Estado: Fuera de Servicio\n");
        mensaje.append("Fecha y Hora de Registro: ").append(this.fechaHoraActual).append("\n\n");
        mensaje.append("Motivos:\n");
        for (String motivo : this.motivosSeleccionados) {
            mensaje.append(" - ").append(motivo).append("\n");
        }
        mensaje.append("\nComentarios:\n");
        for (String comentario : this.comentariosTomados) {
            mensaje.append(" - ").append(comentario).append("\n");
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                mensaje.toString(),
                "CCRS - Alerta de Sismógrafo",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
        });
    }
    public void enviarNotificacionesMail(){
        javax.swing.SwingUtilities.invokeLater(() -> {
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("<html>");
            mensaje.append("<b>De:</b> CCRS Notificaciones &lt;no-reply@ccrs.com&gt;<br>");
            mensaje.append("<b>Para:</b> ");
            for (int i = 0; i < this.mailResponsablesReparaciones.size(); i++) {
                mensaje.append(this.mailResponsablesReparaciones.get(i));
                if (i < this.mailResponsablesReparaciones.size() - 1) {
                    mensaje.append("; ");
                }
            }
            mensaje.append("<br>");
            mensaje.append("<b>Asunto:</b> Notificación de Cambio de Estado - Sismógrafo Fuera de Servicio<br><hr>");
            mensaje.append("<b>Sismógrafo:</b> ").append(this.identificadorSismografo).append("<br>");
            mensaje.append("<b>Estado:</b> Fuera de Servicio<br>");
            mensaje.append("<b>Fecha y Hora de Registro:</b> ").append(this.fechaHoraActual).append("<br><br>");
            mensaje.append("<b>Motivos:</b><ul>");
            for (String motivo : this.motivosSeleccionados) {
                mensaje.append("<li>").append(motivo).append("</li>");
            }
            mensaje.append("</ul>");
            mensaje.append("<b>Comentarios:</b><ul>");
            for (String comentario : this.comentariosTomados) {
                mensaje.append("<li>").append(comentario).append("</li>");
            }
            mensaje.append("</ul>");
            mensaje.append("</html>");

            javax.swing.JOptionPane.showMessageDialog(
                null,
                mensaje.toString(),
                "Gmail - Notificación de Sismógrafo Fuera de Servicio",
                javax.swing.JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(gestorCierreOrden.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png"))
            );
        });
    }
    }




