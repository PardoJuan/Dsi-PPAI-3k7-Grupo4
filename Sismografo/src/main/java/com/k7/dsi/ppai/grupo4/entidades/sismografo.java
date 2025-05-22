package com.k7.dsi.ppai.grupo4.entidades;
import java.util.ArrayList;
public class sismografo{
    private String fechaAdquisicion;
    private String identificadorSismografo;
    private Integer nroSerie;
    private estado estadoActual;
    private cambioEstado cambioEstado;
    private estacionSismologica estacionSismologica;
    
    public sismografo(String fechaAdquisicion, String identificadorSismografo, Integer nroSerie, estado estadoActual, cambioEstado cambioEstado, estacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.estadoActual = estadoActual;
        this.cambioEstado = cambioEstado;
    }
    
    public String getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    public void setFechaAdquisicion(String fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getIdentificadorSismografo(estacionSismologica estacion) {
        if (this.estacionSismologica == estacion) {
            return this.identificadorSismografo;
        }else{
            return null;
        }
    }

    public void setIdentificadorSismografo(String identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }
    public Integer getNroSerie() {
        return nroSerie;
    }
    public void setNroSerie(Integer nroSerie) {
        this.nroSerie = nroSerie;
    }
    public estado getEstadoActual() {
        return estadoActual;
    }
    public void setEstadoActual(estado estadoActual) {
        this.estadoActual = estadoActual;
    }
    public cambioEstado getCambioEstado() {
        return cambioEstado;
    }
   

    public estacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }
    public void setEstacionSismonologica(estacionSismologica estacionSismonologica) {
        this.estacionSismologica = estacionSismonologica;
    }

    public void setCambioEstado(ArrayList<cambioEstado> cambiosEstado, String fechaHoraActual) {
        for (cambioEstado cambioEstado : cambiosEstado) {
            this.buscarCambioEstado(cambioEstado, cambioEstado.getFechaHoraFin());
        }
        this.crearCambioEstado(fechaHoraActual);

    }
    public void buscarCambioEstado(cambioEstado cambioEstado, String fechaHoraActual) {
        if (cambioEstado.getEstado().equals(this.estadoActual)) {
            this.setFechaHoraFin(fechaHoraActual);

        } else {
            this.cambioEstado = null;
        }
    }
    public void setFechaHoraFin(String fechaHoraActual) {
        this.cambioEstado.setFechaHoraFin(fechaHoraActual);
    }

    public void crearCambioEstado(String fechaHoraActual) {
        this.cambioEstado = new cambioEstado( fechaHoraActual, null, new estado("Sismografo","Fuera de Servicio"));
    }
    public String getIdSismografo() {
        return this.identificadorSismografo;
    }
    public void setEstadoSismografo(estado estado) {
        this.estadoActual = estado;
    }

}