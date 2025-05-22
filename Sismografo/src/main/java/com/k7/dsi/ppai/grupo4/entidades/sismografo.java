package com.k7.dsi.ppai.grupo4.entidades;

import java.time.LocalDate;

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

   
    
    public void setFechaHoraFin() {
        this.cambioEstado.setFechaHoraFin(LocalDate.now().toString());
    }
    public String getFechaHoraFin() {
        return this.cambioEstado.getFechaHoraFin();
    }

    public void crearCambioEstado() {
    }
    public String getIdSismografo() {
        return this.identificadorSismografo;
    }
    public void setEstadoSismografo(estado estado) {
        this.estadoActual = estado;
    }

}