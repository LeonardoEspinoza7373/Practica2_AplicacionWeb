package com.energia.rest.utils;

import com.energia.rest.modelo.Proyecto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {
    private static final String ARCHIVO_JSON = "src/main/resources/proyectos.json";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void guardarProyectos(Proyecto[] proyectos) {
        try {
            mapper.writeValue(new File(ARCHIVO_JSON), proyectos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Proyecto[] cargarProyectos() {
        try {
            return mapper.readValue(new File(ARCHIVO_JSON), Proyecto[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Proyecto[0];
        }
    }
}
