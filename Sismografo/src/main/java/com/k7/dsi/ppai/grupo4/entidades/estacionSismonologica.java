package com.k7.dsi.ppai.grupo4.entidades;
import java.sql.Date;
import java.util.ArrayList;

public class estacionSismonologica {
    private String codigoEstacion;
    private String documentoCertificacionAdq;
    private String fechaCertificacion;
    private double latitud;
    private double longitud;
    private String nombre;
    private Integer nroCertificadoAdquisicion;

    public estacionSismonologica(String codigoEstacion, String documentoCertificacionAdq, String fechaCertificacion, double latitud, double longitud, String nombre, Integer nroCertificadoAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionAdq = documentoCertificacionAdq;
        this.fechaCertificacion = fechaCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificadoAdquisicion = nroCertificadoAdquisicion;
    }

    public String getCodigoEstacion() {
        return codigoEstacion;
    }
    public String getNombre(){
        return nombre;
    }

    public String getIdentificadorSismografo(sismografo sismografo) {
        return sismografo.getIdentificadorSismografo();
    }
    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public void enviarAReparar(ArrayList<sismografo> sismografos, ArrayList<cambioEstado> cambiosEstado, String fechaHoraActual) {
        for (sismografo sismografo : sismografos) {
            if (sismografo.getEstacionSismonologica().equals(this)) {
                sismografo.setEstado(cambiosEstado, fechaHoraActual);
            }
        }

    }
}
