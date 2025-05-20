package com.k7.dsi.ppai.grupo4.entidades;

public class muestraSismica {
    private String fechaHoraMuestra;
    private detalleMuestraSismica detalleMuestraSismica;

    public muestraSismica(String fechaHoraMuestra, detalleMuestraSismica detalleMuestraSismica) {
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detalleMuestraSismica = detalleMuestraSismica;
    }
    public String getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }
    public void setFechaHoraMuestra(String fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }
    public detalleMuestraSismica getDetalleMuestraSismica() {
        return detalleMuestraSismica;
    }
    public void setDetalleMuestraSismica(detalleMuestraSismica detalleMuestraSismica) {
        this.detalleMuestraSismica = detalleMuestraSismica;
    }
    
    
}
