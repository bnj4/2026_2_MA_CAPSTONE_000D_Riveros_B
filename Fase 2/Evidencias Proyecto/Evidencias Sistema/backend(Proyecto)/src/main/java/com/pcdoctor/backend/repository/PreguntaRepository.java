package com.pcdoctor.backend.repository;

import com.pcdoctor.backend.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByIdSintoma(Long idSintoma);
}