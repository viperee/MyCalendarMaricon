import { CalendarComponent } from './calendar/calendar.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
    { path: '', component: CalendarComponent }
  ];
  
  @NgModule({
    imports: [RouterModule.forRoot( routes )],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }