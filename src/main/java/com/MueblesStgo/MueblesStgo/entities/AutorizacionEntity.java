package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Autorizacion") // Nombre que adoptará la base de datos
public class AutorizacionEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private LocalDate fechaHoraExtra; // Ejemplo de tipo de dato LocalDate: 2022-09-11
    private String rutEmpleado;
    private float horasExtra;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "Empleado")
    EmpleadoEntity empleado;

    // Métodos (Constructor, getters y setters)
    public AutorizacionEntity(Long id, LocalDate fechaHoraExtra, String rutEmpleado, float horasExtra, EmpleadoEntity empleado) {
        this.id = id;
        this.fechaHoraExtra = fechaHoraExtra;
        this.rutEmpleado = rutEmpleado;
        this.horasExtra = horasExtra;
        this.empleado = empleado;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getFechaHoraExtra() {
        return fechaHoraExtra;
    }
    public void setFechaHoraExtra(LocalDate fechaHoraExtra) {
        this.fechaHoraExtra = fechaHoraExtra;
    }
    public String getRutEmpleado() {
        return rutEmpleado;
    }
    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }
    public float getHorasExtra() {
        return horasExtra;
    }
    public void setHorasExtra(float horasExtra) {
        this.horasExtra = horasExtra;
    }

    public AutorizacionEntity() {
    }
}
