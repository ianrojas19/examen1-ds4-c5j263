export type PrioridadCita = 'ALTA' | 'MEDIA' | 'BAJA';
export type EstadoCita = 'PENDIENTE' | 'EN_ATENCION' | 'ATENDIDA' | 'CANCELADA';

export interface CitaMedica {
  id: number;
  codigoCita: string;
  nombrePaciente: string;
  nombreDoctor: string;
  nivelPrioridad: PrioridadCita;
  motivoConsulta: string;
  estado: EstadoCita;
  montoConsulta: number;
  fechaCita: string;
}

export interface Doctor {
  id: number;
  nombre: string;
  especialidad: string;
  disponible: boolean;
}

export interface Paciente {
  id: number;
  identificacion: string;
  nombre_completo: string;
  correo: string;
  telefono: string;
}

export interface CrearCitaPayload {
  pacienteId: number;
  doctorId: number;
  nivelPrioridad: PrioridadCita;
  motivoConsulta: string;
  montoConsulta: number;
}
