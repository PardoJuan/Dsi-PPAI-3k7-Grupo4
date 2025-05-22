package com.k7.dsi.ppai.grupo4.entidades;
public class usuario {
    private String contrasena;
    private String nombreUsuario;
    private empleado empleado;

    public usuario(String contrasena, String nombreUsuario, empleado empleado) {
        this.contrasena = contrasena;
        this.nombreUsuario = nombreUsuario;
        this.empleado = empleado;
    }
    public empleado getRIlogeado(){
        return this.empleado;
    }
    public empleado getEmpleado() {
        return empleado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "nombreUsuario='" + nombreUsuario + '\'' +
            ", empleado=" + empleado +
            '}';
    }

}