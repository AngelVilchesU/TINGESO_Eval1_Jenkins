package com.MueblesStgo.MueblesStgo.entities;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "MarcaReloj") // Nombre que adoptará la base de datos
public class ArchivoEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private LocalDate fecha; // Ejemplo de tipo de dato LocalDate: 2022-09-10
    private LocalTime horaIngresoSalida; // Ejemplo de tipo de dato LocalTime: 15:40:00
    private String rutEmpleado;

    // Relaciones
    @OneToOne
    @JoinColumn(name = "Empleado")
    EmpleadoEntity empleado;

    // Métodos (Constructor, getters y setters)

    public ArchivoEntity(LocalDate fecha, LocalTime horaIngresoSalida, String rutEmpleado, EmpleadoEntity empleado) {
        this.fecha = fecha;
        this.horaIngresoSalida = horaIngresoSalida;
        this.rutEmpleado = rutEmpleado;
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHoraIngresoSalida() {
        return horaIngresoSalida;
    }
    public void setHoraIngresoSalida(LocalTime horaIngresoSalida) {
        this.horaIngresoSalida = horaIngresoSalida;
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

    public ArchivoEntity() {} // Constructor vacio (controlado)
}
