import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CitaMedica, EstadoCita, PrioridadCita } from '../../models/cita.model';
import { CitaService } from '../../services/cita.service';

type FiltroPrioridad = 'TODAS' | PrioridadCita;

@Component({
  selector: 'app-cita-list',
  imports: [CurrencyPipe, DatePipe, RouterLink],
  templateUrl: './cita-list.component.html'
})
export class CitaListComponent implements OnInit {
  private readonly citaService = inject(CitaService);

  readonly prioridades: FiltroPrioridad[] = ['TODAS', 'ALTA', 'MEDIA', 'BAJA'];
  readonly citas = signal<CitaMedica[]>([]);
  readonly filtro = signal<FiltroPrioridad>('TODAS');
  readonly cargando = signal(true);
  readonly error = signal('');
  readonly actualizandoId = signal<number | null>(null);
  readonly citasFiltradas = computed(() => {
    const prioridad = this.filtro();
    return prioridad === 'TODAS'
      ? this.citas()
      : this.citas().filter((cita) => cita.nivelPrioridad === prioridad);
  });

  ngOnInit(): void {
    this.cargarCitas();
  }

  cargarCitas(): void {
    this.cargando.set(true);
    this.error.set('');
    this.citaService.getCitas().subscribe({
      next: (citas) => {
        this.citas.set(citas);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('No se pudieron cargar las citas. Verifica que el backend esté activo e inténtalo de nuevo.');
        this.cargando.set(false);
      }
    });
  }

  cambiarFiltro(prioridad: FiltroPrioridad): void {
    this.filtro.set(prioridad);
  }

  siguienteEstado(cita: CitaMedica): EstadoCita | null {
    if (cita.estado === 'PENDIENTE') {
      return 'EN_ATENCION';
    }
    return cita.estado === 'EN_ATENCION' ? 'ATENDIDA' : null;
  }

  etiquetaEstado(estado: EstadoCita): string {
    const etiquetas: Record<EstadoCita, string> = {
      PENDIENTE: 'Pendiente',
      EN_ATENCION: 'En atención',
      ATENDIDA: 'Atendida',
      CANCELADA: 'Cancelada'
    };
    return etiquetas[estado];
  }

  avanzarEstado(cita: CitaMedica): void {
    const estado = this.siguienteEstado(cita);
    if (!estado || this.actualizandoId() !== null) {
      return;
    }

    this.actualizandoId.set(cita.id);
    this.error.set('');
    this.citaService.actualizarEstado(cita.id, estado).subscribe({
      next: (actualizada) => {
        this.citas.update((citas) =>
          citas.map((actual) => actual.id === cita.id ? actualizada : actual)
        );
        this.actualizandoId.set(null);
      },
      error: () => {
        this.error.set(`No se pudo actualizar el estado de ${cita.codigoCita}. Inténtalo de nuevo.`);
        this.actualizandoId.set(null);
      }
    });
  }
}
