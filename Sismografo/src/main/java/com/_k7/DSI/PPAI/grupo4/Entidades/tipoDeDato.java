package com._k7.DSI.PPAI.grupo4.Entidades;

public class tipoDeDato {
    private String denominacion;
    private String nombreUnidadMedida;
    private float valorUmbral;

    public tipoDeDato(String denominacion, String nombreUnidadMedida, float valorUmbral) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.valorUmbral = valorUmbral;
    }
    public String getDenominacion() {
        return denominacion;
    }
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }
    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }
    public float getValorUmbral() {
        return valorUmbral;
    }
    public void setValorUmbral(float valorUmbral) {
        this.valorUmbral = valorUmbral;
    }
    
}
