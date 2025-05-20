package com.k7.dsi.ppai.grupo4.entidades;

public class fabricante{
    private String nombre;
    private String razonSocial;

    public fabricante(String nombre, String razonSocial) {
        this.nombre = nombre;
        this.razonSocial = razonSocial;
    }
    public String getNombreFabricante() {
        return nombre;
    }
    public void setNombreFabricante(String nombre) {
        this.nombre = nombre;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}