package com.pcdoctor.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_reparacion")
public class SolicitudReparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_diagnostico", nullable = false)
    private Diagnostico diagnostico;

    @Column(name = "fecha_visita_agendada", nullable = false)
    private LocalDate fechaVisitaAgendada;

    @Column(name = "resumen_respuestas", columnDefinition = "TEXT", nullable = true)
    private String resumenRespuestas;

    @Column(name = "estado", nullable = true)
    private String estado;

    @Column(name = "fecha_solicitud", insertable = false, updatable = false)
    private LocalDateTime fechaSolicitud;

    public SolicitudReparacion() {}

    // Getters y Setters
    public Long getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Long idSolicitud) { this.idSolicitud = idSolicitud; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Diagnostico getDiagnostico() { return diagnostico; }
    public void setDiagnostico(Diagnostico diagnostico) { this.diagnostico = diagnostico; }

    public LocalDate getFechaVisitaAgendada() { return fechaVisitaAgendada; }
    public void setFechaVisitaAgendada(LocalDate fechaVisitaAgendada) { this.fechaVisitaAgendada = fechaVisitaAgendada; }

    public String getResumenRespuestas() { return resumenRespuestas; }
    public void setResumenRespuestas(String resumenRespuestas) { this.resumenRespuestas = resumenRespuestas; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
}