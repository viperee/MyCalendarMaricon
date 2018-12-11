import { Component, OnInit } from '@angular/core';
import { CalendarEvent, CalendarEventAction } from 'angular-calendar'

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  actions: CalendarEventAction[] = [];
  viewDate: Date = new Date();
  events: CalendarEvent[] = [];

  constructor() { }

  ngOnInit() {
  }

}
