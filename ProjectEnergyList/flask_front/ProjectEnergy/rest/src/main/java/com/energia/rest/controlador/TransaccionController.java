package com.energia.rest.controlador;

import com.energia.rest.modelo.Transaccion;
import com.energia.rest.modelo.Historial;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {
    private Historial historial = new Historial();

    @PostMapping("/agregar")
    public Transaccion agregarTransaccion(@RequestBody Transaccion transaccion) {
        transaccion.setFecha(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        historial.agregarTransaccion(transaccion);
        return transaccion;
    }

    @GetMapping
    public Transaccion[] obtenerHistorial() {
        return historial.getTransacciones();
    }
}