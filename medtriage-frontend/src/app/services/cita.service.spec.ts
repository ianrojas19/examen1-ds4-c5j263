import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { CitaMedica, CrearCitaPayload } from '../models/cita.model';
import { CitaService } from './cita.service';

describe('CitaService', () => {
  let service: CitaService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()]
    });
    service = TestBed.inject(CitaService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('loads all appointments from the configured API', () => {
    service.getCitas().subscribe();

    const request = http.expectOne('http://localhost:8080/api/v1/citas');
    expect(request.request.method).toBe('GET');
    request.flush([]);
  });

  it('loads appointments by priority using the backend route', () => {
    service.getCitasPorPrioridad('ALTA').subscribe();

    const request = http.expectOne('http://localhost:8080/api/v1/citas/prioridad/ALTA');
    expect(request.request.method).toBe('GET');
    request.flush([]);
  });

  it('posts the backend appointment payload', () => {
    const payload: CrearCitaPayload = {
      pacienteId: 1,
      doctorId: 2,
      nivelPrioridad: 'MEDIA',
      motivoConsulta: 'Dolor de cabeza',
      montoConsulta: 15000
    };

    service.crearCita(payload).subscribe();

    const request = http.expectOne('http://localhost:8080/api/v1/citas');
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual(payload);
    request.flush({} as CitaMedica);
  });

  it('sends the state as plain text accepted by the backend', () => {
    service.actualizarEstado(3, 'EN_ATENCION').subscribe();

    const request = http.expectOne('http://localhost:8080/api/v1/citas/3/estado');
    expect(request.request.method).toBe('PATCH');
    expect(request.request.body).toBe('EN_ATENCION');
    expect(request.request.headers.get('Content-Type')).toBe('text/plain');
    request.flush({} as CitaMedica);
  });
});
