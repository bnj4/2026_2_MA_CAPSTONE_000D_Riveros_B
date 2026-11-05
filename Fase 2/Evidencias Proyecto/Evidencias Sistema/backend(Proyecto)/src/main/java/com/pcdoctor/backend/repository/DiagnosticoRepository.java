package com.pcdoctor.backend.repository;

import com.pcdoctor.backend.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}