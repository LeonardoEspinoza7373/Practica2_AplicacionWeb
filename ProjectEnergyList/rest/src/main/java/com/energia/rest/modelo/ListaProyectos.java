package com.energia.rest.modelo;

public class ListaProyectos {
    private NodoProyecto cabeza;

    public ListaProyectos() {
        this.cabeza = null;
    }

    public void agregarProyecto(Proyecto proyecto) {
        NodoProyecto nuevoNodo = new NodoProyecto(proyecto);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoProyecto actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public Proyecto[] obtenerProyectos() {
        int contador = 0;
        NodoProyecto actual = cabeza;

        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        Proyecto[] proyectos = new Proyecto[contador];
        actual = cabeza;
        int i = 0;
        while (actual != null) {
            proyectos[i++] = actual.proyecto;
            actual = actual.siguiente;
        }
        return proyectos;
    }

    public boolean eliminarProyecto(String nombre) {
        if (cabeza == null) return false;

        if (cabeza.proyecto.getNombre().equals(nombre)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        NodoProyecto actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.proyecto.getNombre().equals(nombre)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            return true;
        }

        return false;
    }
}
