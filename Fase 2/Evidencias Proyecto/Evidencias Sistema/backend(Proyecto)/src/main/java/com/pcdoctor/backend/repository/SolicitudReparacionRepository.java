package com.pcdoctor.backend.repository;

import com.pcdoctor.backend.model.SolicitudReparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudReparacionRepository extends JpaRepository<SolicitudReparacion, Long> {
}