package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Sueldo") // Nombre que adoptará la base de datos
public class SueldoEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private String rutEmpleado;
    private String nombreApellido;
    private char categoria;
    private float aniosServicio;
    private float sueldoFijoMensual;
    private float montoBonificacionAniosServicio;
    private float pagoHorasExtra;
    private float descuentos;
    private float sueldoBruto;
    private float cotizacionPrevisional;
    private float cotizacionSalud;
    private float montoSueldoFinal;
    private LocalDate fecha;

    // Relaciones
    @OneToOne
    @JoinColumn(name = "Descuento")
    DescuentoEntity descuento;

    // Métodos

    public SueldoEntity(String rutEmpleado, String nombreApellido, char categoria, float aniosServicio, float sueldoFijoMensual, float montoBonificacionAniosServicio, float pagoHorasExtra, float descuentos, float sueldoBruto, float cotizacionPrevisional, float cotizacionSalud, float montoSueldoFinal, LocalDate fecha) {
        this.rutEmpleado = rutEmpleado;
        this.nombreApellido = nombreApellido;
        this.categoria = categoria;
        this.aniosServicio = aniosServicio;
        this.sueldoFijoMensual = sueldoFijoMensual;
        this.montoBonificacionAniosServicio = montoBonificacionAniosServicio;
        this.pagoHorasExtra = pagoHorasExtra;
        this.descuentos = descuentos;
        this.sueldoBruto = sueldoBruto;
        this.cotizacionPrevisional = cotizacionPrevisional;
        this.cotizacionSalud = cotizacionSalud;
        this.montoSueldoFinal = montoSueldoFinal;
        this.fecha = fecha;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRutEmpleado() {
        return rutEmpleado;
    }
    public void setRutEmpleado(String rutEmpleado) {
        this.rutEmpleado = rutEmpleado;
    }
    public String getNombreApellido() {
        return nombreApellido;
    }
    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
    public char getCategoria() {
        return categoria;
    }
    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }
    public float getAniosServicio() {
        return aniosServicio;
    }
    public void setAniosServicio(float aniosServicio) {
        this.aniosServicio = aniosServicio;
    }
    public float getSueldoFijoMensual() {
        return sueldoFijoMensual;
    }
    public void setSueldoFijoMensual(float sueldoFijoMensual) {
        this.sueldoFijoMensual = sueldoFijoMensual;
    }
    public float getMontoBonificacionAniosServicio() {
        return montoBonificacionAniosServicio;
    }
    public void setMontoBonificacionAniosServicio(float montoBonificacionAniosServicio) {
        this.montoBonificacionAniosServicio = montoBonificacionAniosServicio;
    }
    public float getPagoHorasExtra() {
        return pagoHorasExtra;
    }
    public void setPagoHorasExtra(float pagoHorasExtra) {
        this.pagoHorasExtra = pagoHorasExtra;
    }
    public float getDescuentos() {
        return descuentos;
    }
    public void setDescuentos(float descuentos) {
        this.descuentos = descuentos;
    }
    public float getSueldoBruto() {
        return sueldoBruto;
    }
    public void setSueldoBruto(float sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }
    public float getCotizacionPrevisional() {
        return cotizacionPrevisional;
    }
    public void setCotizacionPrevisional(float cotizacionPrevisional) {
        this.cotizacionPrevisional = cotizacionPrevisional;
    }
    public float getCotizacionSalud() {
        return cotizacionSalud;
    }
    public void setCotizacionSalud(float cotizacionSalud) {
        this.cotizacionSalud = cotizacionSalud;
    }
    public float getMontoSueldoFinal() {
        return montoSueldoFinal;
    }
    public void setMontoSueldoFinal(float montoSueldoFinal) {
        this.montoSueldoFinal = montoSueldoFinal;
    }
    public DescuentoEntity getDescuento() {
        return descuento;
    }
    public void setDescuento(DescuentoEntity descuento) {
        this.descuento = descuento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public SueldoEntity() {
    }
}
