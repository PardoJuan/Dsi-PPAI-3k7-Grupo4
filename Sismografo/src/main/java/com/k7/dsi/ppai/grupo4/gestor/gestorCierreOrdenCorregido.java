package com.k7.dsi.ppai.grupo4.gestor;

import java.util.ArrayList;
import java.time.LocalDateTime;
import javax.swing.*;

import com.k7.dsi.ppai.grupo4.entidades.*;

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

    public void buscarOrdenesRealizadas(usuario responsableInspecciones, ArrayList<ordenInspeccion> ordenesRealizadas, ArrayList<sismografo> sismografos) {
        for (ordenInspeccion orden : ordenesRealizadas) {
            if (validarEmpleadoLog(orden.getEmpleado()) && estaRealizada(orden)) {
                System.out.println("2");
                this.nroOrden.add(orden.getNumeroOrden());
                this.fechaHoraFinalizacion.add(orden.getFechaCierre());
                buscarEstacionSismonologica(orden, sismografos);
            }
        }
    }

    public boolean validarEmpleadoLog(empleado empleadoOrden) {
        return empleadoOrden.equals(this.responsableInspecciones.getEmpleado());
    }

    public boolean estaRealizada(ordenInspeccion orden) {
        return "Realizada".equals(orden.getEstado().getNombreEstado());
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

    public void ordenarPorFechaHoraFin() {
        if (this.fechaHoraFinalizacion.isEmpty()) {
            System.out.println("No hay fechas para ordenar");
            return;
        }

        if (fechaHoraFinalizacion.size() == nroOrden.size() && fechaHoraFinalizacion.size() == nombreEstacion.size() && fechaHoraFinalizacion.size() == identificadorSismografo.size()) {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < fechaHoraFinalizacion.size(); i++) {
                indices.add(i);
            }

            indices.sort((i1, i2) -> fechaHoraFinalizacion.get(i1).compareTo(fechaHoraFinalizacion.get(i2)));

            ArrayList<String> fechasOrdenadas = new ArrayList<>();
            ArrayList<Integer> numerosOrdenados = new ArrayList<>();
            ArrayList<String> nombresOrdenados = new ArrayList<>();
            ArrayList<String> identificadoresOrdenados = new ArrayList<>();

            for (int idx : indices) {
                fechasOrdenadas.add(fechaHoraFinalizacion.get(idx));
                numerosOrdenados.add(nroOrden.get(idx));
                nombresOrdenados.add(nombreEstacion.get(idx));
                identificadoresOrdenados.add(identificadorSismografo.get(idx));
            }

            this.fechaHoraFinalizacion = fechasOrdenadas;
            this.nroOrden = numerosOrdenados;
            this.nombreEstacion = nombresOrdenados;
            this.identificadorSismografo = identificadoresOrdenados;
        }
    }

    public ArrayList<String> mostrarOrdenesRealizadas() {
        ArrayList<String> ordenes = new ArrayList<>();

        for (int i = 0; i < fechaHoraFinalizacion.size(); i++) {
            String orden = String.format(
                "Orden N°: %d, Fecha y Hora de Finalización: %s, Nombre de Estación: %s, Identificador Sismógrafo: %s",
                nroOrden.get(i), fechaHoraFinalizacion.get(i), nombreEstacion.get(i), identificadorSismografo.get(i)
            );
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

    public boolean validarExisteObservacionYMotivo() {
        return observacion != null && motivosSeleccionados != null && comentariosTomados != null;
    }

    public void getFechaHoraActual() {
        this.fechaHoraActual = LocalDateTime.now().toString();
    }

    public boolean esAmbitoOrdenInspeccion(estado estado) {
        return "Orden Inspección".equals(estado.getAmbito());
    }

    public boolean esCerrada(estado estado) {
        return "Cerrada".equals(estado.getNombreEstado());
    }

    public void cerrarOrdenInspeccion(estado estado, ordenInspeccion ordenInspeccion) {
        ordenInspeccion.setEstado(estado);
        ordenInspeccion.setFechaHoraCierre(this.fechaHoraActual);
    }

    public ArrayList<Integer> getNroOrden() {
        return nroOrden;
    }

    public void actualizarSismografoBaja(ArrayList<estado> estados, ordenInspeccion ordenInspeccion, ArrayList<sismografo> sismografos, ArrayList<cambioEstado> cambiosEstado) {
        estado estadoSeleccionado = buscarEstadoParaAsignar(estados);
        getFechaHoraActual();
        actualizarSismografoFS(ordenInspeccion, estadoSeleccionado, sismografos, cambiosEstado);
    }

    public estado buscarEstadoParaAsignar(ArrayList<estado> estados) {
        for (estado est : estados) {
            if (esAmbitoSismografo(est) && esFueraDeServicio(est)) {
                return est;
            }
        }
        return null;
    }

    public boolean esAmbitoSismografo(estado estado) {
        return "Sismografo".equals(estado.getAmbito());
    }

    public boolean esFueraDeServicio(estado estado) {
        return "Fuera de Servicio".equals(estado.getNombreEstado());
    }

    public void actualizarSismografoFS(ordenInspeccion ordenInspeccion, estado estado, ArrayList<sismografo> sismografos, ArrayList<cambioEstado> cambiosEstado) {
        ordenInspeccion.actualizarSismografoFS(sismografos, cambiosEstado, this.fechaHoraActual);
    }

    public void obtenerMailResponsablesReparaciones(ArrayList<empleado> empleados) {
        for (empleado emp : empleados) {
            if (emp.esResponsableDeReparacion()) {
                mailResponsablesReparaciones.add(emp.obtenerMail());
            }
        }
    }

    public void publicarEnMonitores() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("⚠️ CCRS - Notificación de Cambio de Estado ⚠️\n\n");
        mensaje.append("Sismógrafo: ").append(identificadorSismografo).append("\n");
        mensaje.append("Estado: Fuera de Servicio\n");
        mensaje.append("Fecha y Hora de Registro: ").append(fechaHoraActual).append("\n\n");
        mensaje.append("Motivos:\n");
        for (String motivo : motivosSeleccionados) {
            mensaje.append(" - ").append(motivo).append("\n");
        }
        mensaje.append("\nComentarios:\n");
        for (String comentario : comentariosTomados) {
            mensaje.append(" - ").append(comentario).append("\n");
        }

        SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(null, mensaje.toString(), "CCRS - Alerta de Sismógrafo", JOptionPane.WARNING_MESSAGE)
        );
    }

    public void enviarNotificacionesMail() {
        SwingUtilities.invokeLater(() -> {
            StringBuilder mensaje = new StringBuilder("<html>");
            mensaje.append("<b>De:</b> CCRS Notificaciones &lt;no-reply@ccrs.com&gt;<br>");
            mensaje.append("<b>Para:</b> ");
            for (int i = 0; i < mailResponsablesReparaciones.size(); i++) {
                mensaje.append(mailResponsablesReparaciones.get(i));
                if (i < mailResponsablesReparaciones.size() - 1) mensaje.append("; ");
            }
            mensaje.append("<br><b>Asunto:</b> Notificación de Cambio de Estado - Sismógrafo Fuera de Servicio<hr>");
            mensaje.append("<b>Sismógrafo:</b> ").append(identificadorSismografo).append("<br>");
            mensaje.append("<b>Estado:</b> Fuera de Servicio<br>");
            mensaje.append("<b>Fecha y Hora de Registro:</b> ").append(fechaHoraActual).append("<br><br>");
            mensaje.append("<b>Motivos:</b><ul>");
            for (String motivo : motivosSeleccionados) mensaje.append("<li>").append(motivo).append("</li>");
            mensaje.append("</ul><b>Comentarios:</b><ul>");
            for (String comentario : comentariosTomados) mensaje.append("<li>").append(comentario).append("</li>");
            mensaje.append("</ul></html>");

            JOptionPane.showMessageDialog(
                null,
                mensaje.toString(),
                "Gmail - Notificación de Sismógrafo Fuera de Servicio",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(gestorCierreOrden.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png"))
            );
        });
    }
}
