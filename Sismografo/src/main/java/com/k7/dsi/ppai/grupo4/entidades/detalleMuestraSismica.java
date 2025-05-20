package com.k7.dsi.ppai.grupo4.entidades;

public class detalleMuestraSismica {
    private float valor;
    private tipoDeDato tipoDeDato;
    
    public detalleMuestraSismica(float valor, tipoDeDato tipoDeDato) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public tipoDeDato getTipoDeDato() {
        return tipoDeDato;
    }
    public void setTipoDeDato(tipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }
    
}
