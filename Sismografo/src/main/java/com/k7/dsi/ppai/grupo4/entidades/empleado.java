package com.k7.dsi.ppai.grupo4.entidades;

public class empleado {
    private String apellido;
    private String nombre;
    private String mail;
    private String telefono;
    private rol rol;

    public empleado(String apellido, String nombre, String mail, String telefono, rol rol) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.rol = rol;
    }
    public String obtenerMail(){
        return this.mail;
    }
    public boolean esResponsableDeReparacion(){
        if (this.rol.getNombre().equals("Responsable de Reparacion")) {
            return true;
        } else {
            return false;
        }
    }
}
