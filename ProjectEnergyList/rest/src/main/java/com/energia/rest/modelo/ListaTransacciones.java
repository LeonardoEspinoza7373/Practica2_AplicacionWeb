package com.energia.rest.modelo;

public class ListaTransacciones {
    private NodoTransaccion cabeza;

    public ListaTransacciones() {
        this.cabeza = null;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        NodoTransaccion nuevoNodo = new NodoTransaccion(transaccion);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoTransaccion actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public Transaccion[] obtenerTransacciones() {
        int contador = 0;
        NodoTransaccion actual = cabeza;

        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        Transaccion[] transacciones = new Transaccion[contador];
        actual = cabeza;
        int i = 0;
        while (actual != null) {
            transacciones[i++] = actual.transaccion;
            actual = actual.siguiente;
        }
        return transacciones;
    }
}
