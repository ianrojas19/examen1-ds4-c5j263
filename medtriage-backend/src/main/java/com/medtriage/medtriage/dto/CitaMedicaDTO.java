package com.medtriage.medtriage.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CitaMedicaDTO {

    private Long id;
    private String codigoCita;
    private String nombrePaciente;
    private String nombreDoctor;
    private String nivelPrioridad;
    private String motivoConsulta;
    private String estado;
    private BigDecimal montoConsulta;
    private LocalDateTime fechaCita;

    public CitaMedicaDTO() {
    }

    public CitaMedicaDTO(
            Long id,
            String codigoCita,
            String nombrePaciente,
            String nombreDoctor,
            String nivelPrioridad,
            String motivoConsulta,
            String estado,
            BigDecimal montoConsulta,
            LocalDateTime fechaCita) {

        this.id = id;
        this.codigoCita = codigoCita;
        this.nombrePaciente = nombrePaciente;
        this.nombreDoctor = nombreDoctor;
        this.nivelPrioridad = nivelPrioridad;
        this.motivoConsulta = motivoConsulta;
        this.estado = estado;
        this.montoConsulta = montoConsulta;
        this.fechaCita = fechaCita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getNivelPrioridad() {
        return nivelPrioridad;
    }

    public void setNivelPrioridad(String nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMontoConsulta() {
        return montoConsulta;
    }

    public void setMontoConsulta(BigDecimal montoConsulta) {
        this.montoConsulta = montoConsulta;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }
}
