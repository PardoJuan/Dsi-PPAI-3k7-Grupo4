package com.k7.dsi.ppai.grupo4.entidades;



public class motivoFueraServicio{
    private String comentario;
    private motivoTipo motivoTipo;

    // Constructor
    public motivoFueraServicio(String comentario, motivoTipo motivoTipo) {
        this.comentario = comentario;
        this.motivoTipo = motivoTipo;
    }
    // Getters y Setters
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public motivoTipo getMotivoTipo() {
        return motivoTipo;
    }
    public void setMotivoTipo(motivoTipo motivoTipo) {
        this.motivoTipo = motivoTipo;
    }
    
}