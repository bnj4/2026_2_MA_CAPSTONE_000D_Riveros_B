package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Diagnostico;
import com.pcdoctor.backend.repository.DiagnosticoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/diagnosticos", "/api/diagnostico"})
@CrossOrigin(origins = "*")
public class DiagnosticoController {

    private final DiagnosticoRepository diagnosticoRepository;

    public DiagnosticoController(DiagnosticoRepository diagnosticoRepository) {
        this.diagnosticoRepository = diagnosticoRepository;
    }

    @GetMapping
    public List<Diagnostico> obtenerTodos() {
        return diagnosticoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> obtenerPorId(@PathVariable("id") Long id) {
        return diagnosticoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}