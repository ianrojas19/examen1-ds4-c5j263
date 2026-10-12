import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CitaMedica, CrearCitaPayload, EstadoCita, PrioridadCita } from '../models/cita.model';

@Injectable({ providedIn: 'root' })
export class CitaService {
  private readonly http = inject(HttpClient);
  private readonly citasUrl = `${environment.API_URL}citas`;

  getCitas(): Observable<CitaMedica[]> {
    return this.http.get<CitaMedica[]>(this.citasUrl);
  }

  getCitasPorPrioridad(prioridad: PrioridadCita): Observable<CitaMedica[]> {
    return this.http.get<CitaMedica[]>(`${this.citasUrl}/prioridad/${prioridad}`);
  }

  crearCita(payload: CrearCitaPayload): Observable<CitaMedica> {
    return this.http.post<CitaMedica>(this.citasUrl, payload);
  }

  actualizarEstado(id: number, estado: EstadoCita): Observable<CitaMedica> {
    return this.http.patch<CitaMedica>(
      `${this.citasUrl}/${id}/estado`,
      estado,
      { headers: { 'Content-Type': 'text/plain' } }
    );
  }
}
