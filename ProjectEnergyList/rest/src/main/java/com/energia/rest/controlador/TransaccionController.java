package com.energia.rest.controlador;

import com.energia.rest.dao.TransaccionDAO;
import com.energia.rest.modelo.Transaccion;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {
    private TransaccionDAO transaccionDAO = new TransaccionDAO();

    @PostMapping("/agregar")
    public Transaccion agregarTransaccion(@RequestBody Transaccion transaccion) {
        transaccion.setFecha(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        transaccionDAO.agregarTransaccion(transaccion);
        return transaccion;
    }

    @GetMapping
    public Transaccion[] obtenerHistorial() {
        return transaccionDAO.obtenerHistorial();
    }
}
