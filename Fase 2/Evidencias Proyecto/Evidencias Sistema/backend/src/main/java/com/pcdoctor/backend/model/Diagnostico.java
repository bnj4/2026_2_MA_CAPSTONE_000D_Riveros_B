package com.pcdoctor.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico")
    private Long idDiagnostico;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "causa_probable", nullable = false, columnDefinition = "TEXT")
    private String causaProbable;

    @Column(name = "solucion_recomendada", nullable = false, columnDefinition = "TEXT")
    private String solucionRecomendada;

    @Column(name = "presupuesto_estimado_clp", nullable = false)
    private Double presupuestoEstimadoClp;

    public Diagnostico() {}

    public Diagnostico(Long idDiagnostico, String titulo, String causaProbable, String solucionRecomendada, Double presupuestoEstimadoClp) {
        this.idDiagnostico = idDiagnostico;
        this.titulo = titulo;
        this.causaProbable = causaProbable;
        this.solucionRecomendada = solucionRecomendada;
        this.presupuestoEstimadoClp = presupuestoEstimadoClp;
    }

    public Long getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(Long idDiagnostico) { this.idDiagnostico = idDiagnostico; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getCausaProbable() { return causaProbable; }
    public void setCausaProbable(String causaProbable) { this.causaProbable = causaProbable; }

    public String getSolucionRecomendada() { return solucionRecomendada; }
    public void setSolucionRecomendada(String solucionRecomendada) { this.solucionRecomendada = solucionRecomendada; }

    public Double getPresupuestoEstimadoClp() { return presupuestoEstimadoClp; }
    public void setPresupuestoEstimadoClp(Double presupuestoEstimadoClp) { this.presupuestoEstimadoClp = presupuestoEstimadoClp; }
}