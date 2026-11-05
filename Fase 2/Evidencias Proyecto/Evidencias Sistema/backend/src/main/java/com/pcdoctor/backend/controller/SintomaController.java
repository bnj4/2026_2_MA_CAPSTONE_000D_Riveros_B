package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Sintoma;
import com.pcdoctor.backend.repository.SintomaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/sintomas", "/api/sintoma"})
@CrossOrigin(origins = "*")
public class SintomaController {

    private final SintomaRepository sintomaRepository;

    public SintomaController(SintomaRepository sintomaRepository) {
        this.sintomaRepository = sintomaRepository;
    }

    @GetMapping
    public List<Sintoma> obtenerTodos() {
        return sintomaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sintoma> obtenerPorId(@PathVariable("id") Long id) {
        return sintomaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}