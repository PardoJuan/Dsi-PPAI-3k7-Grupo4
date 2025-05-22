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
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public rol getRol() {
        return rol;
    }
    public void setRol(rol rol) {
        this.rol = rol;
    }
    

    @Override
    public String toString() {
        return "Empleado{" +
            "nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", mail='" + mail + '\'' +
            '}';
    }

}
