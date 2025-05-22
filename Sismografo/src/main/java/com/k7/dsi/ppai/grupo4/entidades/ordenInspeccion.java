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

    public ordenInspeccion(String fechaHoraCierre, String fechaHoraFinalizacion, String fechaHoraInicio, Integer numeroOrden, String observacionCierre, estado estado, empleado empleado, estacionSismologica estacionSismologica) {
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

    public String getFechaFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraCierre(String fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public boolean validarEmpleadoLog(empleado responsableInspecciones) {
        return (responsableInspecciones == this.empleado);
    }

    public boolean estaRealizada(){
        return this.estado.esRealizada();
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
    
    public ArrayList<Object> buscarEstacionSismologica(ArrayList<sismografo> sismografos){
        ArrayList<Object> datos = new ArrayList<>();
        datos.add(estacionSismologica.getNombre());
        datos.add(estacionSismologica.getIdentificadorSismografo(sismografos));
        return datos;
    }


    
    public void setObservacionCierre(String observacionCierre) {
        this.observacionCierre = observacionCierre;
    }
}
