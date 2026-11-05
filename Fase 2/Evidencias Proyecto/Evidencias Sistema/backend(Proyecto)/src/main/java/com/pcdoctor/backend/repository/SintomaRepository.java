package com.pcdoctor.backend.repository;

import com.pcdoctor.backend.model.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SintomaRepository extends JpaRepository<Sintoma, Long> {
}