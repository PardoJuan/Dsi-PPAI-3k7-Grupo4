package com.k7.dsi.ppai.grupo4.entidades;
import java.util.ArrayList;

public class estacionSismologica {
    private String codigoEstacion;
    private String documentoCertificacionAdq;
    private String fechaCertificacion;
    private double latitud;
    private double longitud;
    private String nombre;
    private Integer nroCertificadoAdquisicion;

    public estacionSismologica(String codigoEstacion, String documentoCertificacionAdq, String fechaCertificacion, double latitud, double longitud, String nombre, Integer nroCertificadoAdquisicion) {
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

    public String getIdentificadorSismografo(ArrayList<sismografo> sismografos){
        for (sismografo sismografo : sismografos) {
            String identificador = sismografo.getIdentificadorSismografo(this);
            if (identificador != null) {
                return identificador;
            }
        }
        return null;
    }


    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    
}
