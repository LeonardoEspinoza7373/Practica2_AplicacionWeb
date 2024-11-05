package com.energia.rest.dao;

import com.energia.rest.modelo.Transaccion;
import com.energia.rest.modelo.ListaTransacciones;

public class TransaccionDAO {
    private ListaTransacciones listaTransacciones;

    public TransaccionDAO() {
        this.listaTransacciones = new ListaTransacciones();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        listaTransacciones.agregarTransaccion(transaccion);
    }

    public Transaccion[] obtenerHistorial() {
        return listaTransacciones.obtenerTransacciones();
    }
}
