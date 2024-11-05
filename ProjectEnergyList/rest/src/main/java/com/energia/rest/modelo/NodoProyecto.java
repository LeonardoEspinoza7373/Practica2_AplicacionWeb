package com.energia.rest.modelo;

public class NodoProyecto {
    public Proyecto proyecto;
    public NodoProyecto siguiente;

    public NodoProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        this.siguiente = null;
    }
}
