package com.MueblesStgo.MueblesStgo.entities;
import javax.persistence.*;
import java.time.LocalTime;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Descuento") // Nombre que adoptará la base de datos
public class DescuentoEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private LocalTime tiempoTrabajo;
    private LocalTime tiempoRetraso;
    private float montoDescuento;
    private float cotizacionPrevisional;
    private float cotizacionPlanSalud;

    // Métodos (Constructor, getters y setters)

    public DescuentoEntity(Long id, LocalTime tiempoTrabajo, LocalTime tiempoRetraso, float montoDescuento, float cotizacionPrevisional, float cotizacionPlanSalud) {
        this.id = id;
        this.tiempoTrabajo = tiempoTrabajo;
        this.tiempoRetraso = tiempoRetraso;
        this.montoDescuento = montoDescuento;
        this.cotizacionPrevisional = cotizacionPrevisional;
        this.cotizacionPlanSalud = cotizacionPlanSalud;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalTime getTiempoRetraso() {
        return tiempoRetraso;
    }
    public void setTiempoRetraso(LocalTime tiempoRetraso) {
        this.tiempoRetraso = tiempoRetraso;
    }
    public float getMontoDescuento() {
        return montoDescuento;
    }
    public void setMontoDescuento(float montoDescuento) {
        this.montoDescuento = montoDescuento;
    }
    public float getCotizacionPrevisional() {
        return cotizacionPrevisional;
    }
    public void setCotizacionPrevisional(float cotizacionPrevisional) {
        this.cotizacionPrevisional = cotizacionPrevisional;
    }
    public float getCotizacionPlanSalud() {
        return cotizacionPlanSalud;
    }
    public void setCotizacionPlanSalud(float cotizacionPlanSalud) {
        this.cotizacionPlanSalud = cotizacionPlanSalud;
    }

    public LocalTime getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    public void setTiempoTrabajo(LocalTime tiempoTrabajo) {
        this.tiempoTrabajo = tiempoTrabajo;
    }

    public DescuentoEntity() {
    }
}
