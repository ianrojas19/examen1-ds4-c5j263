import { Routes } from '@angular/router';
import { CitaFormComponent } from './components/cita-form/cita-form.component';
import { CitaListComponent } from './components/cita-list/cita-list.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'citas' },
  { path: 'citas', component: CitaListComponent },
  { path: 'nueva-cita', component: CitaFormComponent },
  { path: '**', redirectTo: 'citas' }
];
