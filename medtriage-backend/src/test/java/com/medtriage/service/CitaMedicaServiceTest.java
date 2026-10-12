package com.medtriage.service;

import com.medtriage.medtriage.dto.CitaMedicaDTO;
import com.medtriage.medtriage.dto.CrearCitaDTO;
import com.medtriage.medtriage.model.CitaMedica;
import com.medtriage.medtriage.model.Doctor;
import com.medtriage.medtriage.model.Paciente;
import com.medtriage.medtriage.repository.CitaMedicaRepository;
import com.medtriage.medtriage.repository.DoctorRepository;
import com.medtriage.medtriage.repository.PacienteRepository;
import com.medtriage.medtriage.service.CitaMedicaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private CitaMedicaService citaMedicaService;

    @Test
    void crearCita_guardaUnaCitaPendienteConCodigoUnico() {
        Paciente paciente = new Paciente(
                1L, "118230495", "Elena Madrigal Castro", "elena@ucr.ac.cr", "88776655");
        Doctor doctor = new Doctor(1L, "Dr. Carlos Alvarado", "Medicina General", true);

        CrearCitaDTO solicitud = new CrearCitaDTO();
        solicitud.setPacienteId(1L);
        solicitud.setDoctorId(1L);
        solicitud.setNivelPrioridad("alta");
        solicitud.setMotivoConsulta(" Fiebre alta ");
        solicitud.setMontoConsulta(new BigDecimal("25000.00"));

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(citaRepository.count()).thenReturn(0L);
        when(citaRepository.existsByCodigoCita("CIT-2026-0001")).thenReturn(false);
        when(citaRepository.save(any(CitaMedica.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CitaMedicaDTO resultado = citaMedicaService.crearCita(solicitud);

        ArgumentCaptor<CitaMedica> citaCaptor = ArgumentCaptor.forClass(CitaMedica.class);
        verify(citaRepository).save(citaCaptor.capture());
        CitaMedica guardada = citaCaptor.getValue();

        assertEquals("CIT-2026-0001", resultado.getCodigoCita());
        assertEquals("PENDIENTE", resultado.getEstado());
        assertEquals("ALTA", resultado.getNivelPrioridad());
        assertEquals("Elena Madrigal Castro", resultado.getNombrePaciente());
        assertEquals("Dr. Carlos Alvarado", resultado.getNombreDoctor());
        assertEquals("CIT-2026-0001", guardada.getCodigoCita());
        assertEquals("PENDIENTE", guardada.getEstado());
        assertEquals(" Fiebre alta ", guardada.getMotivoConsulta());
        assertEquals(new BigDecimal("25000.00"), guardada.getMontoConsulta());
    }
}
