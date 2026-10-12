import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CrearCitaPayload } from '../../models/cita.model';
import { CitaService } from '../../services/cita.service';

@Component({
  selector: 'app-cita-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './cita-form.component.html'
})
export class CitaFormComponent {
  private readonly citaService = inject(CitaService);
  private readonly router = inject(Router);

  readonly guardando = signal(false);
  readonly error = signal('');

  form: CrearCitaPayload = {
    pacienteId: 0,
    doctorId: 0,
    nivelPrioridad: 'MEDIA',
    motivoConsulta: '',
    montoConsulta: 0
  };

  enviar(): void {
    this.guardando.set(true);
    this.error.set('');

    this.citaService.crearCita(this.form).subscribe({
      next: () => {
        void this.router.navigate(['/citas']);
      },
      error: (error: unknown) => {
        this.error.set(this.mensajeError(error));
        this.guardando.set(false);
      }
    });
  }

  private mensajeError(error: unknown): string {
    if (typeof error === 'object' && error !== null && 'error' in error) {
      const response = error.error;
      if (typeof response === 'object' && response !== null && 'detail' in response &&
          typeof response.detail === 'string') {
        return response.detail;
      }
    }
    return 'No fue posible registrar la cita. Revisa los datos y confirma que el backend esté disponible.';
  }
}
