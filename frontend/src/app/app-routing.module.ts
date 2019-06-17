import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddRuleComponent } from './components/add-rule/add-rule.component';
import { SeeLogsComponent } from './components/see-logs/see-logs.component';
import { AlarmsComponent } from './components/alarms/alarms.component';

const routes: Routes = [
  { path: '', redirectTo: 'add-rule', pathMatch: 'full' },
  { path: 'add-rule', component: AddRuleComponent, pathMatch: 'full' },
  { path: 'see-logs', component: SeeLogsComponent, pathMatch: 'full' },
  { path: 'alarms', component: AlarmsComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
