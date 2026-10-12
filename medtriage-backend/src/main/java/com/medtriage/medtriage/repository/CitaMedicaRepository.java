package com.medtriage.medtriage.repository;

import com.medtriage.medtriage.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

    List<CitaMedica> findAllByOrderByFechaCitaDesc();

    List<CitaMedica> findByNivelPrioridadOrderByFechaCitaDesc(String prioridad);

    boolean existsByCodigoCita(String codigo);
}
