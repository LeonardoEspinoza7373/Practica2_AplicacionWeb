package com.energia.rest.controlador;

import com.energia.rest.modelo.ListaProyectos;
import com.energia.rest.modelo.Proyecto;
import com.energia.rest.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private ListaProyectos proyectos = new ListaProyectos();

    @GetMapping
    public Proyecto[] obtenerProyectos() {
        return proyectos.obtenerProyectos();
    }

    @PostMapping("/agregar")
    public Proyecto agregarProyecto(@RequestBody Proyecto proyecto) {
        proyectos.agregarProyecto(proyecto);
        JsonUtils.guardarProyectos(proyectos.obtenerProyectos());
        return proyecto;
    }

    @DeleteMapping("/eliminar/{nombre}")
    public void eliminarProyecto(@PathVariable String nombre) {
        proyectos.eliminarProyecto(nombre);
        JsonUtils.guardarProyectos(proyectos.obtenerProyectos());
    }
}
