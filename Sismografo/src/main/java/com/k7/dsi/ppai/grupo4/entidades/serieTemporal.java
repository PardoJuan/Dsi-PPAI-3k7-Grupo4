package com.k7.dsi.ppai.grupo4.entidades;
public class serieTemporal{
    private String condicionAlarma;
    private String fechaHoraInicioRegistroMuestras;
    private String fechaHoraRegistro;
    private String frecuenciaMuestreo;
    private muestraSismica muestraSismica;


    public serieTemporal(String condicionAlarma, String fechaHoraInicioRegistroMuestras, 
    String fechaHoraRegistro, String frecuenciaMuestreo, muestraSismica muestraSismica){
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.muestraSismica = muestraSismica;
    }
    public String getCondicionAlarma() {
        return condicionAlarma;
    }
    public void setCondicionAlarma(String condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }
    public String getFechaHoraInicioRegistroMuestras() {
        return fechaHoraInicioRegistroMuestras;
    }
    public void setFechaHoraInicioRegistroMuestras(String fechaHoraInicioRegistroMuestras) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
    }
    public String getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }
    public void setFechaHoraRegistro(String fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }
    public String getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }
    public void setFrecuenciaMuestreo(String frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }
    public muestraSismica getMuestraSismica() {
        return muestraSismica;
    }
    public void setMuestraSismica(muestraSismica muestraSismica) {
        this.muestraSismica = muestraSismica;
    }
    
    


}