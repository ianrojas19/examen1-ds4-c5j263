package com.medtriage.medtriage.service;

import com.medtriage.medtriage.dto.CitaMedicaDTO;
import com.medtriage.medtriage.dto.CrearCitaDTO;
import com.medtriage.medtriage.model.*;
import com.medtriage.medtriage.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class CitaMedicaService {

    private final CitaMedicaRepository citaRepository;
    private final DoctorRepository doctorRepository;
    private final PacienteRepository pacienteRepository;

    public CitaMedicaService(CitaMedicaRepository c,
            DoctorRepository d,
            PacienteRepository p) {
        citaRepository = c;
        doctorRepository = d;
        pacienteRepository = p;
    }

    private String generarCodigoCita() {
        long numero = citaRepository.count() + 1;
        String codigo;

        do {
            codigo = "CIT-2026-" + String.format("%04d", numero++);
        } while (citaRepository.existsByCodigoCita(codigo));

        return codigo;
    }

    @Transactional(readOnly = true)
    public List<CitaMedicaDTO> obtenerTodas() {
        return citaRepository.findAllByOrderByFechaCitaDesc()
                .stream().map(this::convertirDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<CitaMedicaDTO> obtenerPorPrioridad(String prioridad) {
        if(prioridad == null || prioridad.isBlank()) {
            throw new IllegalArgumentException("Prioridad obligatoria");
        }
        return citaRepository
                .findByNivelPrioridadOrderByFechaCitaDesc(
                        prioridad.toUpperCase(Locale.ROOT).trim())
                .stream().map(this::convertirDTO).toList();
    }

    @Transactional
    public CitaMedicaDTO crearCita(CrearCitaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        if (!doctor.isDisponible()) {
            throw new RuntimeException("Doctor no disponible");
        }

        CitaMedica cita = new CitaMedica();
        cita.setCodigoCita(generarCodigoCita());
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setNivelPrioridad(dto.getNivelPrioridad().toUpperCase().trim());
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setEstado("PENDIENTE");
        cita.setMontoConsulta(dto.getMontoConsulta());
        cita.setFechaCita(LocalDateTime.now());

        return convertirDTO(citaRepository.save(cita));
    }

    @Transactional
    public CitaMedicaDTO actualizarEstado(Long id, String nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("Estado obligatorio");
        }

        nuevoEstado = nuevoEstado.toUpperCase().trim();

        if (!nuevoEstado.equals("EN_ATENCION")
                && !nuevoEstado.equals("ATENDIDA")
                && !nuevoEstado.equals("CANCELADA")) {
            throw new IllegalArgumentException("Estado inválido");
        }

        CitaMedica cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado(nuevoEstado);

        return convertirDTO(citaRepository.save(cita));
    }

    private CitaMedicaDTO convertirDTO(CitaMedica c) {
        return new CitaMedicaDTO(
                c.getId(),
                c.getCodigoCita(),
                c.getPaciente().getNombre_completo(),
                c.getDoctor().getNombre(),
                c.getNivelPrioridad(),
                c.getMotivoConsulta(),
                c.getEstado(),
                c.getMontoConsulta(),
                c.getFechaCita());
    }
}