import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddRuleComponent } from './components/add-rule/add-rule.component';

const routes: Routes = [
  { path: '', redirectTo: 'add-rule', pathMatch: 'full' },
  { path: 'add-rule', component: AddRuleComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
