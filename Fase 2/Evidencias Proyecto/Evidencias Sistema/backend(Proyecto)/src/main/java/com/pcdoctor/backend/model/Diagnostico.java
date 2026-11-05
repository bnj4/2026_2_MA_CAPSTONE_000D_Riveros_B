package com.pcdoctor.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico")
    private Long idDiagnostico;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "nivel_probabilidad")
    private String nivelProbabilidad;

    @Column(name = "causa_probable")
    private String causaProbable;

    @Column(name = "solucion_recomendada")
    private String solucionRecomendada;

    @Column(name = "diagnostico_secundario")
    private String diagnosticoSecundario;

    @Column(name = "precio_estimado")
    private BigDecimal precioEstimado;

    public Diagnostico() {}

    // Getters y Setters
    public Long getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Long idDiagnostico) { this.idDiagnostico = idDiagnostico; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getNivelProbabilidad() { return nivelProbabilidad; }
    public void setNivelProbabilidad(String nivelProbabilidad) { this.nivelProbabilidad = nivelProbabilidad; }

    public String getCausaProbable() { return causaProbable; }
    public void setCausaProbable(String causaProbable) { this.causaProbable = causaProbable; }

    public String getSolucionRecomendada() { return solucionRecomendada; }
    public void setSolucionRecomendada(String solucionRecomendada) { this.solucionRecomendada = solucionRecomendada; }

    public String getDiagnosticoSecundario() { return diagnosticoSecundario; }
    public void setDiagnosticoSecundario(String diagnosticoSecundario) { this.diagnosticoSecundario = diagnosticoSecundario; }

    public BigDecimal getPrecioEstimado() { return precioEstimado; }
    public void setPrecioEstimado(BigDecimal precioEstimado) { this.precioEstimado = precioEstimado; }
}