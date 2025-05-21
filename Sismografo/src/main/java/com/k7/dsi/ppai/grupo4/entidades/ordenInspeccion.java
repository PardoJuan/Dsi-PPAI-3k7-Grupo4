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
    private estacionSismonologica estacionSismonologica;

    public ordenInspeccion(String fechaHoraCierre, String fechaHoraFinalizacion, String fechaHoraInicio, Integer numeroOrden, String observacionCierre, estado estado, empleado empleado, estacionSismonologica estacionSismonologica) {
        this.estacionSismonologica = estacionSismonologica;
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
    public estacionSismonologica getEstacionSismonologica() {
        return estacionSismonologica;
    }
    public String getNombre() {
        return estacionSismonologica.getNombre();
    }

    public void actualizarSismografoFS(ArrayList<sismografo> sismografos, ArrayList<cambioEstado> cambiosEstado, String fechaHoraActual) {
        this.estacionSismonologica.enviarAReparar(sismografos, cambiosEstado, fechaHoraActual);
    }
    
}
