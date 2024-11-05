package com.energia.rest.controlador;

import com.energia.rest.modelo.Proyecto;
import com.energia.rest.modelo.Transaccion;
import com.energia.rest.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private Proyecto[] proyectos = new Proyecto[100]; // Arreglo fijo para almacenar proyectos
    private int contadorProyectos = 0;

    @GetMapping
    public Proyecto[] obtenerProyectos() {
        // Devuelve solo los proyectos que se han agregado
        Proyecto[] proyectosActivos = new Proyecto[contadorProyectos];
        System.arraycopy(proyectos, 0, proyectosActivos, 0, contadorProyectos);
        return proyectosActivos;
    }

    @PostMapping("/agregar")
    public Proyecto agregarProyecto(@RequestBody Proyecto proyecto) {
        if (contadorProyectos < proyectos.length) {
            proyectos[contadorProyectos] = proyecto;
            contadorProyectos++;
            // Guarda el cambio en el archivo JSON
            JsonUtils.guardarProyectos(proyectos);
            return proyecto;
        } else {
            throw new RuntimeException("No hay espacio para más proyectos.");
        }
    }

    @DeleteMapping("/eliminar/{nombre}")
    public void eliminarProyecto(@PathVariable String nombre) {
        for (int i = 0; i < contadorProyectos; i++) {
            if (proyectos[i] != null && proyectos[i].getNombre().equals(nombre)) {
                proyectos[i] = null;
                // Mueve los elementos restantes hacia adelante
                for (int j = i; j < contadorProyectos - 1; j++) {
                    proyectos[j] = proyectos[j + 1];
                }
                proyectos[contadorProyectos - 1] = null;
                contadorProyectos--;
                // Guarda el cambio en el archivo JSON
                JsonUtils.guardarProyectos(proyectos);
                break;
            }
        }
    }
}