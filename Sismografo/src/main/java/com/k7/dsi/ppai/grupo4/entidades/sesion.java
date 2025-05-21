package com.k7.dsi.ppai.grupo4.entidades;

public class sesion{
    private String fechaDesde;
    private String fechaHasta;
    private usuario usuario;

    public sesion(String fechaDesde, String fechaHasta, usuario usuario) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.usuario = usuario;
    }
    public usuario conocerRI(){
        return this.usuario;
    }
}