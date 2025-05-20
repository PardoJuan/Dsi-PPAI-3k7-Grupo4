package com.k7.dsi.ppai.grupo4.entidades;

public class reparacion {
    private String comentariosReparacion;
    private String comentariosResolucionReparacion;
    private String fechaEnvioReparacion;
    private String fechaRespuestaReparacion;
    private Integer nroReparacion;

    public reparacion(String comentariosReparacion, String comentariosResolucionReparacion, String fechaEnvioReparacion, String fechaRespuestaReparacion, Integer nroReparacion) {
        this.comentariosReparacion = comentariosReparacion;
        this.comentariosResolucionReparacion = comentariosResolucionReparacion;
        this.fechaEnvioReparacion = fechaEnvioReparacion;
        this.fechaRespuestaReparacion = fechaRespuestaReparacion;
        this.nroReparacion = nroReparacion;
    }
    public String getComentariosReparacion() {
        return comentariosReparacion;
    }
    public void setComentariosReparacion(String comentariosReparacion) {
        this.comentariosReparacion = comentariosReparacion;
    }
    public String getComentariosResolucionReparacion() {
        return comentariosResolucionReparacion;
    }
    public void setComentariosResolucionReparacion(String comentariosResolucionReparacion) {
        this.comentariosResolucionReparacion = comentariosResolucionReparacion;
    }
    public String getFechaEnvioReparacion() {
        return fechaEnvioReparacion;
    }
    public void setFechaEnvioReparacion(String fechaEnvioReparacion) {
        this.fechaEnvioReparacion = fechaEnvioReparacion;
    }
    public String getFechaRespuestaReparacion() {
        return fechaRespuestaReparacion;
    }
    public void setFechaRespuestaReparacion(String fechaRespuestaReparacion) {
        this.fechaRespuestaReparacion = fechaRespuestaReparacion;
    }
    public Integer getNroReparacion() {
        return nroReparacion;
    }
    public void setNroReparacion(Integer nroReparacion) {
        this.nroReparacion = nroReparacion;
    }
    
}
