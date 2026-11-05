package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Cliente;
import com.pcdoctor.backend.model.Diagnostico;
import com.pcdoctor.backend.model.SolicitudReparacion;
import com.pcdoctor.backend.repository.ClienteRepository;
import com.pcdoctor.backend.repository.DiagnosticoRepository;
import com.pcdoctor.backend.repository.SolicitudReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudReparacionController {

    @Autowired
    private SolicitudReparacionRepository solicitudRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        try {
            List<SolicitudReparacion> lista = solicitudRepository.findAll();
            List<Map<String, Object>> respuesta = new ArrayList<>();

            for (SolicitudReparacion s : lista) {
                Map<String, Object> item = new HashMap<>();
                item.put("idSolicitud", s.getIdSolicitud());
                item.put("id_solicitud", s.getIdSolicitud());
                item.put("fechaVisitaAgendada", s.getFechaVisitaAgendada() != null ? s.getFechaVisitaAgendada().toString() : "");
                item.put("resumenRespuestas", s.getResumenRespuestas() != null ? s.getResumenRespuestas() : "Sin historial registrado");
                item.put("estado", s.getEstado() != null ? s.getEstado() : "PENDIENTE");

                // Datos Cliente
                Map<String, Object> cliMap = new HashMap<>();
                if (s.getCliente() != null) {
                    cliMap.put("idCliente", s.getCliente().getIdCliente());
                    cliMap.put("nombreCompleto", s.getCliente().getNombreCompleto());
                    cliMap.put("credencialRut", s.getCliente().getCredencialRut());
                    cliMap.put("correo", s.getCliente().getCorreo());
                    cliMap.put("telefono", s.getCliente().getTelefono());
                } else {
                    cliMap.put("nombreCompleto", "Cliente Desconocido");
                    cliMap.put("credencialRut", "N/A");
                    cliMap.put("correo", "N/A");
                    cliMap.put("telefono", "N/A");
                }
                item.put("cliente", cliMap);

                // Datos Diagnóstico
                Map<String, Object> diagMap = new HashMap<>();
                if (s.getDiagnostico() != null) {
                    diagMap.put("idDiagnostico", s.getDiagnostico().getIdDiagnostico());
                    diagMap.put("titulo", s.getDiagnostico().getTitulo());
                    diagMap.put("precioEstimado", s.getDiagnostico().getPrecioEstimado());
                } else {
                    diagMap.put("titulo", "Diagnóstico General");
                    diagMap.put("precioEstimado", "25000");
                }
                item.put("diagnostico", diagMap);

                respuesta.add(item);
            }

            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error en servidor: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearSolicitud(@RequestBody Map<String, Object> payload) {
        try {
            Long idCliente = Long.valueOf(payload.get("idCliente").toString());
            Long idDiagnostico = Long.valueOf(payload.get("idDiagnostico").toString());
            String fechaVisita = payload.get("fechaVisita").toString();
            String resumen = payload.get("resumenRespuestas") != null ? payload.get("resumenRespuestas").toString() : "Sin historial registrado";

            Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
            Diagnostico diagnostico = diagnosticoRepository.findById(idDiagnostico).orElse(null);

            if (cliente == null || diagnostico == null) {
                return ResponseEntity.badRequest().body("Cliente o Diagnóstico no encontrado");
            }

            SolicitudReparacion solicitud = new SolicitudReparacion();
            solicitud.setCliente(cliente);
            solicitud.setDiagnostico(diagnostico);
            solicitud.setFechaVisitaAgendada(LocalDate.parse(fechaVisita));
            solicitud.setResumenRespuestas(resumen);
            solicitud.setEstado("PENDIENTE");

            SolicitudReparacion guardada = solicitudRepository.save(solicitud);
            return ResponseEntity.ok(guardada);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al agendar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSolicitud(@PathVariable Long id) {
        try {
            solicitudRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al borrar");
        }
    }
}