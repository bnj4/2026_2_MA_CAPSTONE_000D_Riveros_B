package com.pcdoctor.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sintomas")
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sintoma")
    private Long idSintoma;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public Sintoma() {}

    public Sintoma(Long idSintoma, String nombre, String descripcion) {
        this.idSintoma = idSintoma;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdSintoma() { return idSintoma; }
    public void setIdSintoma(Long idSintoma) { this.idSintoma = idSintoma; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}