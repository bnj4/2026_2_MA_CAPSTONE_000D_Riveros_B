package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Cliente;
import com.pcdoctor.backend.model.Diagnostico;
import com.pcdoctor.backend.model.SolicitudReparacion;
import com.pcdoctor.backend.repository.ClienteRepository;
import com.pcdoctor.backend.repository.DiagnosticoRepository;
import com.pcdoctor.backend.repository.SolicitudReparacionRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/solicitudes", "/api/solicitud"})
@CrossOrigin(origins = "*")
public class SolicitudReparacionController {

    private final SolicitudReparacionRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final DiagnosticoRepository diagnosticoRepository;

    public SolicitudReparacionController(
            SolicitudReparacionRepository solicitudRepository,
            ClienteRepository clienteRepository,
            DiagnosticoRepository diagnosticoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.diagnosticoRepository = diagnosticoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerTodas() {
        List<SolicitudReparacion> lista = solicitudRepository.findAll();
        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (SolicitudReparacion sol : lista) {
            Map<String, Object> map = new HashMap<>();
            map.put("idSolicitud", sol.getIdSolicitud());
            map.put("resumenRespuestas", sol.getResumenRespuestas());
            map.put("presupuestoTotal", sol.getPresupuestoTotal());
            map.put("fechaVisita", sol.getFechaVisita() != null ? sol.getFechaVisita().toString() : "");
            map.put("estado", sol.getEstado());

            if (sol.getCliente() != null) {
                Map<String, Object> cliMap = new HashMap<>();
                cliMap.put("idCliente", sol.getCliente().getIdCliente());
                cliMap.put("rut", sol.getCliente().getRut());
                cliMap.put("nombre", sol.getCliente().getNombre());
                cliMap.put("correo", sol.getCliente().getCorreo());
                cliMap.put("telefono", sol.getCliente().getTelefono());
                map.put("cliente", cliMap);
                map.put("rut", sol.getCliente().getRut());
                map.put("nombre", sol.getCliente().getNombre());
                map.put("correo", sol.getCliente().getCorreo());
                map.put("telefono", sol.getCliente().getTelefono());
            }

            if (sol.getDiagnostico() != null) {
                map.put("idDiagnostico", sol.getDiagnostico().getIdDiagnostico());
                map.put("tituloDiagnostico", sol.getDiagnostico().getTitulo());
            }

            respuesta.add(map);
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<?> crearSolicitud(@RequestBody Map<String, Object> payload) {
        try {
            String rut = (String) payload.get("rut");
            String nombre = (String) payload.get("nombre");
            String correo = (String) payload.get("correo");
            String telefono = (String) payload.get("telefono");
            String resumenRespuestas = (String) payload.get("resumenRespuestas");
            String fechaVisitaStr = (String) payload.get("fechaVisita");
            String estado = payload.get("estado") != null ? (String) payload.get("estado") : "PENDIENTE";

            Number presupuestoNum = (Number) payload.get("presupuestoTotal");
            Double presupuestoTotal = presupuestoNum != null ? presupuestoNum.doubleValue() : 0.0;

            Number idDiagNum = (Number) payload.get("idDiagnostico");
            Long idDiagnostico = idDiagNum != null ? idDiagNum.longValue() : null;

            Cliente cliente = clienteRepository.findByRut(rut).orElseGet(() -> {
                Cliente nuevo = new Cliente();
                nuevo.setRut(rut);
                nuevo.setNombre(nombre);
                nuevo.setCorreo(correo);
                nuevo.setTelefono(telefono);
                return clienteRepository.save(nuevo);
            });

            if (cliente.getIdCliente() != null) {
                cliente.setNombre(nombre);
                cliente.setCorreo(correo);
                cliente.setTelefono(telefono);
                clienteRepository.save(cliente);
            }

            Diagnostico diagnostico = null;
            if (idDiagnostico != null) {
                diagnostico = diagnosticoRepository.findById(idDiagnostico).orElse(null);
            }

            SolicitudReparacion solicitud = new SolicitudReparacion();
            solicitud.setCliente(cliente);
            solicitud.setDiagnostico(diagnostico);
            solicitud.setResumenRespuestas(resumenRespuestas);
            solicitud.setPresupuestoTotal(presupuestoTotal);
            if (fechaVisitaStr != null && !fechaVisitaStr.isEmpty()) {
                solicitud.setFechaVisita(LocalDate.parse(fechaVisitaStr));
            } else {
                solicitud.setFechaVisita(LocalDate.now().plusDays(1));
            }
            solicitud.setEstado(estado);

            SolicitudReparacion guardada = solicitudRepository.save(solicitud);

            Map<String, Object> resp = new HashMap<>();
            resp.put("idSolicitud", guardada.getIdSolicitud());
            resp.put("mensaje", "Solicitud registrada con éxito");
            resp.put("rut", cliente.getRut());

            return ResponseEntity.ok(resp);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error procesando agendamiento: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSolicitud(@PathVariable("id") Long id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
            Map<String, String> resp = new HashMap<>();
            resp.put("mensaje", "Solicitud eliminada con éxito");
            return ResponseEntity.ok(resp);
        }
        return ResponseEntity.notFound().build();
    }
}