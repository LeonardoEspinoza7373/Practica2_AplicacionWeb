package com.energia.rest.modelo;

public class Transaccion {
    private String tipo;
    private String descripcion;
    private String fecha;

    public Transaccion(String tipo, String descripcion, String fecha) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}