package com.k7.dsi.ppai.grupo4.entidades;

public class modeloSismografo{
    private String caracteristicas;
    private String nombreModelo;
    private fabricante fabricante;

    public modeloSismografo(String caracteristicas, String nombreModelo, fabricante fabricante) {
        this.caracteristicas = caracteristicas;
        this.nombreModelo = nombreModelo;
        this.fabricante = fabricante;
    }
    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public String getNombreModelo() {
        return nombreModelo;
    }
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    public fabricante getFabricante() {
        return fabricante;
    }
    public void setFabricante(fabricante fabricante) {
        this.fabricante = fabricante;
    }
    
}