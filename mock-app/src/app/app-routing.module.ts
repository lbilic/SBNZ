import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SendLogComponent } from './components/send-log/send-log.component';
import { SeeLogsComponent } from './components/see-logs/see-logs.component';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'send-log', component: SendLogComponent, pathMatch: 'full' },
  { path: 'see-logs', component: SeeLogsComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
