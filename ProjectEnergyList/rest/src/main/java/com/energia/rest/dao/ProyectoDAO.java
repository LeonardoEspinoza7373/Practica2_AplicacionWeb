package com.energia.rest.dao;

import com.energia.rest.modelo.ListaProyectos;
import com.energia.rest.modelo.Proyecto;
import com.energia.rest.utils.JsonUtils;

public class ProyectoDAO {
    private ListaProyectos listaProyectos;

    public ProyectoDAO() {
        this.listaProyectos = new ListaProyectos();
        cargarProyectos();
    }

    public void agregarProyecto(Proyecto proyecto) {
        listaProyectos.agregarProyecto(proyecto);
        guardarProyectos();
    }

    public Proyecto[] obtenerProyectos() {
        return listaProyectos.obtenerProyectos();
    }

    public boolean eliminarProyecto(String nombre) {
        boolean eliminado = listaProyectos.eliminarProyecto(nombre);
        if (eliminado) {
            guardarProyectos();
        }
        return eliminado;
    }

    private void guardarProyectos() {
        JsonUtils.guardarProyectos(obtenerProyectos());
    }

    private void cargarProyectos() {
        Proyecto[] proyectos = JsonUtils.cargarProyectos();
        for (Proyecto proyecto : proyectos) {
            if (proyecto != null) {
                listaProyectos.agregarProyecto(proyecto);
            }
        }
    }
}
