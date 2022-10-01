package com.MueblesStgo.MueblesStgo.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Justificativo") // Nombre que adoptará la base de datos
public class JustificativoEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private LocalDate fechaInasistencia; // Ejemplo de tipo de dato LocalDate: 2002-08-04
    private String rutEmpleado;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "Empleado")
    EmpleadoEntity empleado;

    // Métodos (Constructor, getters y setters)
    public JustificativoEntity(Long id, LocalDate fechaInasistencia, String rutEmpleado) {
        this.id = id;
        this.fechaInasistencia = fechaInasistencia;
        this.rutEmpleado = rutEmpleado;
    }
    public String getRutEmpleado() {
        return rutEmpleado;
    }
    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getFechaInasistencia() {
        return fechaInasistencia;
    }
    public void setFechaInasistencia(LocalDate fechaInasistencia) {
        this.fechaInasistencia = fechaInasistencia;
    }
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }
    public JustificativoEntity() {}
}
