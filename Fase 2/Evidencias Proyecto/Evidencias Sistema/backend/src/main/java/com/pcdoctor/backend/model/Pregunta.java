package com.pcdoctor.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Long idPregunta;

    @Column(name = "id_sintoma", nullable = false)
    private Long idSintoma;

    @Column(name = "texto_pregunta", nullable = false, columnDefinition = "TEXT")
    private String textoPregunta;

    @Column(name = "id_siguiente_si")
    private Long idSiguienteSi;

    @Column(name = "id_siguiente_no")
    private Long idSiguienteNo;

    @Column(name = "id_diagnostico_si")
    private Long idDiagnosticoSi;

    @Column(name = "id_diagnostico_no")
    private Long idDiagnosticoNo;

    public Pregunta() {}

    public Long getIdPregunta() { return idPregunta; }
    public void setIdPregunta(Long idPregunta) { this.idPregunta = idPregunta; }

    public Long getIdSintoma() { return idSintoma; }
    public void setIdSintoma(Long idSintoma) { this.idSintoma = idSintoma; }

    public String getTextoPregunta() { return textoPregunta; }
    public void setTextoPregunta(String textoPregunta) { this.textoPregunta = textoPregunta; }

    public Long getIdSiguienteSi() { return idSiguienteSi; }
    public void setIdSiguienteSi(Long idSiguienteSi) { this.idSiguienteSi = idSiguienteSi; }

    public Long getIdSiguienteNo() { return idSiguienteNo; }
    public void setIdSiguienteNo(Long idSiguienteNo) { this.idSiguienteNo = idSiguienteNo; }

    public Long getIdDiagnosticoSi() { return idDiagnosticoSi; }
    public void setIdDiagnosticoSi(Long idDiagnosticoSi) { this.idDiagnosticoSi = idDiagnosticoSi; }

    public Long getIdDiagnosticoNo() { return idDiagnosticoNo; }
    public void setIdDiagnosticoNo(Long idDiagnosticoNo) { this.idDiagnosticoNo = idDiagnosticoNo; }
}