
package com.medtriage.medtriage.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita_medica")
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_cita", nullable = false, unique = true, length = 30)
    private String codigoCita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "paciente_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_cita_paciente")
    )
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "doctor_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_cita_doctor")
    )
    private Doctor doctor;

    @Column(name = "nivel_prioridad", nullable = false, length = 20)
    private String nivelPrioridad;

    @Column(name = "motivo_consulta", nullable = false, length = 255)
    private String motivoConsulta;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(name = "monto_consulta", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoConsulta;

    @Column(name = "fecha_cita", nullable = false)
    private LocalDateTime fechaCita;

    public CitaMedica() {
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
