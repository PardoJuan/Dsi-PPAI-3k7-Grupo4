package com.k7.dsi.ppai.grupo4.entidades;

import java.util.ArrayList;

public class ordenInspeccion {
    private String fechaHoraCierre;
    private String fechaHoraFinalizacion;
    private String fechaHoraInicio;
    private Integer numeroOrden;
    private String observacionCierre;
    private estado estado;
    private empleado empleado;
    private estacionSismologica estacionSismologica;

    public ordenInspeccion(String fechaHoraCierre, String fechaHoraFinalizacion, String fechaHoraInicio, Integer numeroOrden, String observacionCierre, estado estado, empleado empleado, estacionSismologica estacionSismonologica) {
        this.estacionSismologica = estacionSismologica;
        this.fechaHoraCierre = fechaHoraCierre;
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.numeroOrden = numeroOrden;
        this.observacionCierre = observacionCierre;
        this.estado = estado;
        this.empleado = empleado;
    }
    public String getFechaCierre() {
        return fechaHoraCierre;
    }
    public void setFechaHoraCierre(String fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }
    
    public Integer getNumeroOrden() {
        return numeroOrden;
    }
    
    public void setEstado(estado estado) {
        this.estado = estado;
    }
    public empleado getEmpleado() {
        return empleado;
    }

    public estado getEstado() {
        return estado;
    }
    public estacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }
    public String getNombre() {
        return estacionSismologica.getNombre();
    }

    public void actualizarSismografoFS(ArrayList<sismografo> sismografos, ArrayList<cambioEstado> cambiosEstado, String fechaHoraActual) {
        this.estacionSismologica.enviarAReparar(sismografos, cambiosEstado, fechaHoraActual);
    }
    
}
