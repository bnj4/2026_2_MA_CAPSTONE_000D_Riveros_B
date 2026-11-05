package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Pregunta;
import com.pcdoctor.backend.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
@CrossOrigin(origins = "*")
public class PreguntaController {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @GetMapping
    public List<Pregunta> obtenerTodas() {
        return preguntaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pregunta obtenerPorId(@PathVariable("id") Long id) {
        return preguntaRepository.findById(id).orElse(null);
    }

    @GetMapping("/sintoma/{idSintoma}")
    public List<Pregunta> obtenerPorSintoma(@PathVariable("idSintoma") Long idSintoma) {
        return preguntaRepository.findByIdSintoma(idSintoma);
    }
}