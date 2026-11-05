package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Diagnostico;
import com.pcdoctor.backend.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/diagnosticos", "/api/diagnostico"})
@CrossOrigin(origins = "*")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @GetMapping
    public List<Diagnostico> obtenerTodos() {
        return diagnosticoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Diagnostico obtenerPorId(@PathVariable Long id) {
        return diagnosticoRepository.findById(id).orElse(null);
    }

    @GetMapping("/inicial/{parametro}")
    public Diagnostico obtenerDiagnosticoInicial(@PathVariable String parametro) {
        try {
            String idStr = parametro.contains(":") ? parametro.split(":")[0] : parametro;
            Long id = Long.parseLong(idStr);
            return diagnosticoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}