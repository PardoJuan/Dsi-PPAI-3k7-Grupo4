package com.k7.dsi.ppai.grupo4.entidades;
import java.sql.Date;
public class sismografo{
    private Date fechaAdquisicion;
    private String identificadorSismografo;
    private Integer nroSerie;
    private estacionSismologica estacionSismologica;
    private reparacion repacion;
    private modeloSismografo modeloSismografo;
    private serieTemporal serieTemporal;
    private estado estadoActual;
    private cambioEstado cambioEstado;
    
    public sismografo(Date fechaAdquisicion, String identificadorSismografo, Integer nroSerie, estacionSismologica estacionSismologica, reparacion repacion, modeloSismografo modeloSismografo, serieTemporal serieTemporal, estado estadoActual, cambioEstado cambioEstado) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.estacionSismologica = estacionSismologica;
        this.repacion = repacion;
        this.modeloSismografo = modeloSismografo;
        this.serieTemporal = serieTemporal;
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
    public estacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }
    public void setEstacionSismologica(estacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }
    public reparacion getRepacion() {
        return repacion;
    }
    public void setRepacion(reparacion repacion) {
        this.repacion = repacion;
    }
    public modeloSismografo getModeloSismografo() {
        return modeloSismografo;
    }
    public void setModeloSismografo(modeloSismografo modeloSismografo) {
        this.modeloSismografo = modeloSismografo;
    }
    public serieTemporal getSerieTemporal() {
        return serieTemporal;
    }
    public void setSerieTemporal(serieTemporal serieTemporal) {
        this.serieTemporal = serieTemporal;
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





}