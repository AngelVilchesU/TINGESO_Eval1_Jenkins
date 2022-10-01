package com.MueblesStgo.MueblesStgo.entities;
import javax.persistence.*;

@Entity // Indica que corresponde a una entidad de persistencia
@Table(name = "Categoria") // Nombre que adoptará la base de datos
public class CategoriaEntity {

    // Atributos
    @Id // Permite que la BD visualice el ID como tal
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generado automáticamente e incrementable
    @Column(unique = true, nullable = false) // Es único y no puede ser nulo
    private Long id;
    private char categoria;
    private float sueldoFijoMensual;
    private float montoPorHora;

    // Métodos (Constructor, getters y setters)
    public CategoriaEntity(Long id, char categoria, float sueldoFijoMensual, float montoPorHora) {
        this.id = id;
        this.categoria = categoria;
        this.sueldoFijoMensual = sueldoFijoMensual;
        this.montoPorHora = montoPorHora;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public char getCategoria() {
        return categoria;
    }
    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }
    public float getSueldoFijoMensual() {
        return sueldoFijoMensual;
    }
    public void setSueldoFijoMensual(float sueldoFijoMensual) {
        this.sueldoFijoMensual = sueldoFijoMensual;
    }
    public float getMontoPorHora() {
        return montoPorHora;
    }
    public void setMontoPorHora(float montoPorHora) {
        this.montoPorHora = montoPorHora;
    }
    public CategoriaEntity() {
    }
}
