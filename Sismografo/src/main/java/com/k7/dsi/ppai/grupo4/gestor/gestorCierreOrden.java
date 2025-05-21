package com.k7.dsi.ppai.grupo4.gestor;
import java.util.ArrayList;

import com.k7.dsi.ppai.grupo4.entidades.empleado;
import com.k7.dsi.ppai.grupo4.entidades.motivoTipo;
import com.k7.dsi.ppai.grupo4.entidades.ordenInspeccion;
import com.k7.dsi.ppai.grupo4.entidades.sesion;
import com.k7.dsi.ppai.grupo4.entidades.sismografo;
import com.k7.dsi.ppai.grupo4.entidades.usuario;
import com.k7.dsi.ppai.grupo4.entidades.estado;
public class gestorCierreOrden {
    private usuario responsableInspecciones;
    private ArrayList<Integer> nroOrden;
    private ArrayList<String> fechaHoraFinalizacion;
    private ArrayList<String> nombreEstacion;
    private ArrayList<String> identificadorSismografo;
    private String observacion;
    private ArrayList<String> motivosSeleccionados;
    private ArrayList<String> comentariosTomados;
    private String fechaHoraActual;


    public void conocerRI(sesion sesion) {
        this.responsableInspecciones = sesion.conocerRI().getRIlogeado();

    }

    public void buscarOrdenesRealizadas(usuario responsableInspecciones , ArrayList<ordenInspeccion> ordenesRealizadas, ArrayList<sismografo> sismografos) {
        for (ordenInspeccion orden : ordenesRealizadas) {
            if (this.validarEmpleadoLog(orden.getEmpleado())) {
                if(this.estaRealizada(orden)){
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
        for (sismografo sismografo : sismografos) {
            if (sismografo.getEstacionSismonologica().equals(orden.getEstacionSismonologica())) {
                this.identificadorSismografo.add(orden.getEstacionSismonologica().getIdentificadorSismografo(sismografo));
            } else {
                this.identificadorSismografo.add("No se encontro el sismografo");
            }
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
    public void actualizarSismografoBaja(ArrayList<estado> estado, ordenInspeccion ordenInspeccion) {
        estado estadoSelecionado = this.buscarEstadoParaAsignar(estado);
        this.getFechaHoraActual();
        this.actualizarSismografoFS(ordenInspeccion, estadoSelecionado);

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
    public void actualizarSismografoFS(ordenInspeccion ordenInspeccion, estado estado) {
        
    }
    
}
