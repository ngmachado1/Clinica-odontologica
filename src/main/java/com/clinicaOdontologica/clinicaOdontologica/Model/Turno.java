package com.clinicaOdontologica.clinicaOdontologica.Model;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Turno {
    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate date;

    public Turno() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turnos = (Turno) o;
        return Objects.equals(id, turnos.id) && Objects.equals(paciente, turnos.paciente) && Objects.equals(odontologo, turnos.odontologo) && Objects.equals(date, turnos.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paciente, odontologo, date);
    }

    @Override
    public String toString() {
        return "Turnos{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
