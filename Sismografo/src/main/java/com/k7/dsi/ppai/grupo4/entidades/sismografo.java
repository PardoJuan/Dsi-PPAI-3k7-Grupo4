package com.k7.dsi.ppai.grupo4.entidades;
import java.sql.Date;
public class sismografo{
    private Date fechaAdquisicion;
    private String identificadorSismografo;
    private Integer nroSerie;
    private estado estadoActual;
    private cambioEstado cambioEstado;
    private estacionSismonologica estacionSismonologica;
    
    public sismografo(Date fechaAdquisicion, String identificadorSismografo, Integer nroSerie, estado estadoActual, cambioEstado cambioEstado, estacionSismonologica estacionSismonologica) {
        this.estacionSismonologica = estacionSismonologica;
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.estadoActual = estadoActual;
        this.cambioEstado = cambioEstado;
    }
    
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
    public String getIdentificadorSismografo() {
        return identificadorSismografo;
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
    public void setCambioEstado(cambioEstado cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public estacionSismonologica getEstacionSismonologica() {
        return estacionSismonologica;
    }
    public void setEstacionSismonologica(estacionSismonologica estacionSismonologica) {
        this.estacionSismonologica = estacionSismonologica;
    }




}