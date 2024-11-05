package com.energia.rest.modelo;

public class Historial {
    private Transaccion[] transacciones;
    private int contador;

    public Historial() {
        this.transacciones = new Transaccion[10]; // Tamaño inicial
        this.contador = 0;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        if (contador >= transacciones.length) {
            redimensionarArray();
        }
        transacciones[contador++] = transaccion;
    }

    public Transaccion[] getTransacciones() {
        Transaccion[] copia = new Transaccion[contador];
        System.arraycopy(transacciones, 0, copia, 0, contador);
        return copia;
    }

    private void redimensionarArray() {
        Transaccion[] nuevoArray = new Transaccion[transacciones.length * 2];
        System.arraycopy(transacciones, 0, nuevoArray, 0, transacciones.length);
        transacciones = nuevoArray;
    }
}