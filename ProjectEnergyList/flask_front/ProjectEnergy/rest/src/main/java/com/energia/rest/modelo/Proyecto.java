package com.energia.rest.modelo;

public class Proyecto {
    private String nombre;
    private double inversion;
    private int tiempoVida;
    private String fechaInicioConstruccion;
    private String fechaFinConstruccion;
    private double electricidadPorDia;
    private String[] inversionistas;

    public Proyecto(String nombre, double inversion, int tiempoVida, String fechaInicioConstruccion,
                    String fechaFinConstruccion, double electricidadPorDia, String[] inversionistas) {
        this.nombre = nombre;
        this.inversion = inversion;
        this.tiempoVida = tiempoVida;
        this.fechaInicioConstruccion = fechaInicioConstruccion;
        this.fechaFinConstruccion = fechaFinConstruccion;
        this.electricidadPorDia = electricidadPorDia;
        this.inversionistas = inversionistas;
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Otros getters y setters, si es necesario
}