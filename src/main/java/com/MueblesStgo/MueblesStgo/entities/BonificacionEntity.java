package com.MueblesStgo.MueblesStgo.entities;

import javax.persistence.*;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Bonificacion") // Nombre que adoptará la base de datos
public class BonificacionEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private float aniosServicio;
    private float bono;

    // Métodos (Constructor, getters y setters)
    public BonificacionEntity(Long id, float aniosServicio, float bono) {
        this.id = id;
        this.aniosServicio = aniosServicio;
        this.bono = bono;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public float getAniosServicio() {
        return aniosServicio;
    }
    public void setAniosServicio(float aniosServicio) {
        this.aniosServicio = aniosServicio;
    }
    public float getBono() {
        return bono;
    }
    public void setBono(float bono) {
        this.bono = bono;
    }
    public BonificacionEntity() {
    }
}
