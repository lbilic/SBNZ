import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddRuleComponent } from './components/add-rule/add-rule.component';
import { SeeLogsComponent } from './components/see-logs/see-logs.component';
import { AlarmsComponent } from './components/alarms/alarms.component';
import { CustomRuleComponent } from './components/custom-rule/custom-rule.component';
import { FindUsersComponent } from './components/find-users/find-users.component';

const routes: Routes = [
  { path: '', redirectTo: 'add-rule', pathMatch: 'full' },
  { path: 'add-rule', component: AddRuleComponent, pathMatch: 'full' },
  { path: 'see-logs', component: SeeLogsComponent, pathMatch: 'full' },
  { path: 'alarms', component: AlarmsComponent, pathMatch: 'full' },
  { path: 'custom-rule', component: CustomRuleComponent, pathMatch: 'full' },
  { path: 'find-users', component: FindUsersComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
