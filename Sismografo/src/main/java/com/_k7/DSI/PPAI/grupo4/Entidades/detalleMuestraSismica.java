package com._k7.DSI.PPAI.grupo4.Entidades;

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
