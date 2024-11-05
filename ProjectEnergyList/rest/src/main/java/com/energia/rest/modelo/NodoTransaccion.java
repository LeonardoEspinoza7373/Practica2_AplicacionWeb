package com.energia.rest.modelo;

public class NodoTransaccion {
    public Transaccion transaccion;
    public NodoTransaccion siguiente;

    public NodoTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
        this.siguiente = null;
    }
}
