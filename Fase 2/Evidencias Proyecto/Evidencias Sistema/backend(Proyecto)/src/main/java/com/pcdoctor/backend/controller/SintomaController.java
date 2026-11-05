package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Sintoma;
import com.pcdoctor.backend.repository.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sintomas")
@CrossOrigin(origins = "*")
public class SintomaController {

    @Autowired
    private SintomaRepository sintomaRepository;

    @GetMapping
    public List<Sintoma> obtenerTodos() {
        return sintomaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Sintoma obtenerPorId(@PathVariable("id") Long id) {
        return sintomaRepository.findById(id).orElse(null);
    }
}