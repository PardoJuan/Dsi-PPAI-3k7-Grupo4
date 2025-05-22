package com.k7.dsi.ppai.grupo4.entidades;

public class estado {
    private String ambito;
    private String nombreEstado;

    public estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }
    public String getAmbito() {
        return ambito;
    }
    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
    public String getNombreEstado() {
        return nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    public boolean esRealizada(){
        return (this.nombreEstado == "Realizada");
    }
    
}
