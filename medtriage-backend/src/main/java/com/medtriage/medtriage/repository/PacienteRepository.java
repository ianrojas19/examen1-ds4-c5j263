package com.medtriage.medtriage.repository;

import com.medtriage.medtriage.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
