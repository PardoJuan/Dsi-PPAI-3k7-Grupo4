package com._k7.DSI.PPAI.grupo4.Entidades;

import java.sql.Date;

public class estacionSismologica{
    private String codigoEstacion;
    private String documentoCertificacionAdq;
    private Date fechaSolicitudCertificacion;
    private double latitud;
    private double longitud;
    private String nombre;
    private Integer nroCertificacionAdquisicion;

    public estacionSismologica(String codigoEstacion, String documentoCertificacionAdq, Date fechaSolicitudCertificacion, double latitud, double longitud, String nombre, Integer nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionAdq = documentoCertificacionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
    public String getCodigoEstacion() {
        return codigoEstacion;
    }
    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }
    public String getDocumentoCertificacionAdq() {
        return documentoCertificacionAdq;
    }
    public void setDocumentoCertificacionAdq(String documentoCertificacionAdq) {
        this.documentoCertificacionAdq = documentoCertificacionAdq;
    }
    public Date getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }
    public void setFechaSolicitudCertificacion(Date fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }
    public void setNroCertificacionAdquisicion(Integer nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
    
}