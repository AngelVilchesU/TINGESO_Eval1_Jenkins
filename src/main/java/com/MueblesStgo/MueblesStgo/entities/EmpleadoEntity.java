package com.MueblesStgo.MueblesStgo.entities;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Empleado") // Nombre que adoptará la base de datos
public class EmpleadoEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento; // Ejemplo de tipo de dato LocalDate: 2022-10-10
    private LocalDate fechaIngresoEmpresa;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "Categoria")
    CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "Bonificacion")
    BonificacionEntity bonificacion;

    @ManyToOne
    @JoinColumn(name = "Sueldo")
    SueldoEntity sueldo;



    // Métodos (Constructor, getters y setters)
    public EmpleadoEntity(Long id, String rut, String nombre, String apellido, LocalDate fechaNacimiento, LocalDate fechaIngresoEmpresa) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngresoEmpresa = fechaIngresoEmpresa;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
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
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public LocalDate getFechaIngresoEmpresa() {
        return fechaIngresoEmpresa;
    }
    public void setFechaIngresoEmpresa(LocalDate fechaIngresoEmpresa) {
        this.fechaIngresoEmpresa = fechaIngresoEmpresa;
    }
    public CategoriaEntity getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
    public BonificacionEntity getBonificacion() {
        return bonificacion;
    }
    public void setBonificacion(BonificacionEntity bonificacion) {
        this.bonificacion = bonificacion;
    }
    public SueldoEntity getSueldo() {
        return sueldo;
    }
    public void setSueldo(SueldoEntity sueldo) {
        this.sueldo = sueldo;
    }
    public EmpleadoEntity() {
    }
}
