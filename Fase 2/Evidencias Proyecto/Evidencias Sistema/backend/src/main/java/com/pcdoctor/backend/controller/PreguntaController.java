package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Pregunta;
import com.pcdoctor.backend.repository.PreguntaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/preguntas", "/api/pregunta"})
@CrossOrigin(origins = "*")
public class PreguntaController {

    private final PreguntaRepository preguntaRepository;

    public PreguntaController(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    @GetMapping
    public List<Pregunta> obtenerTodas() {
        return preguntaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pregunta> obtenerPorId(@PathVariable("id") Long id) {
        return preguntaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sintoma/{idSintoma}")
    public List<Pregunta> obtenerPorSintoma(@PathVariable("idSintoma") Long idSintoma) {
        return preguntaRepository.findByIdSintoma(idSintoma);
    }
}