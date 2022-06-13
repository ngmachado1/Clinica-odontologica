package com.clinicaOdontologica.clinicaOdontologica.Model;

import java.time.LocalDate;

public class Paciente {
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fecha_ingreso;
    private int domicilio_id;

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFechaIngreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDomicilio_id() {
        return domicilio_id;
    }

    public void setDomicilio_id(int domicilio_id) {
        this.domicilio_id = domicilio_id;
    }
}
