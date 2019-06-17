import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SendLogComponent } from './components/send-log/send-log.component';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'send-log', component: SendLogComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
