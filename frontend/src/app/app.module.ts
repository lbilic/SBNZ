import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddRuleComponent } from './components/add-rule/add-rule.component';
import { SeeLogsComponent } from './components/see-logs/see-logs.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { MatFormFieldModule, MatOptionModule, MatSelectModule, MatInputModule, MatButtonModule } from '@angular/material';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list'; 
import { MatTableModule } from '@angular/material/table';
import { AlarmsComponent } from './components/alarms/alarms.component';


@NgModule({
  declarations: [
    AppComponent,
    AddRuleComponent,
    SeeLogsComponent,
    NavbarComponent,
    AlarmsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatOptionModule,
    MatSelectModule,
    MatExpansionModule,
    MatListModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
