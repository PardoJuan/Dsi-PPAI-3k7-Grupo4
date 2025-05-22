package com.k7.dsi.ppai.grupo4.entidades;

public class cambioEstado {
    private String fechaHoraFin;
    private String fechaHoraInicio;
    private estado estado;
    private motivoFueraServicio motivoFueraServicio;

    public cambioEstado(String fechaHoraFin, String fechaHoraInicio, estado estado) {
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
    }   
    
    public cambioEstado(String fechaHoraFin, String fechaHoraInicio, estado estado, motivoFueraServicio motivoFueraServicio) {
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.motivoFueraServicio = motivoFueraServicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }
    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }
    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }
    public estado getEstado() {
        return estado;
    }
    public void setEstado(estado estado) {
        this.estado = estado;
    }
    
}
