package com.medtriage.medtriage.repository;

import com.medtriage.medtriage.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

    List<CitaMedica> findByNivelPrioridadOrderByFechaCitaDesc(String nivelPrioridad);

    List<CitaMedica> findAllByOrderByFechaCitaDesc();

    boolean existsByCodigoCita(String codigoCita);
}
