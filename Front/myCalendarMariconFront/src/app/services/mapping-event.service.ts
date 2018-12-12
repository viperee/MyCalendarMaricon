import { Couleur } from "./../models/couleur";
import { Injectable } from "@angular/core";
import { Event } from "../models/event";
import { CalendarEvent } from "calendar-utils";

@Injectable({
  providedIn: "root"
})
export class MappingEventService {
  constructor() {}

  convertCalendarEventToEvent(calendarEvent: CalendarEvent): Event {
    return new Event(
      calendarEvent.title,
      calendarEvent.start.toDateString(),
      calendarEvent.end.toDateString(),
      new Couleur(calendarEvent.color.primary, calendarEvent.color.secondary),
      calendarEvent.allDay
    );
  }

  convertEventToCalendarEvent(event: Event) {
    return {
      id: event._id,
      title: event._titre,
      start: new Date(event._dateDebut),
      end: new Date(event._dateFin),
      color: {
        primary: event._couleurs._primary,
        secondary:event._couleurs._secondary
      },
      draggable: true,
      resizable: {
        beforeStart: true,
        afterEnd: true
      },
      alldays: event._journeeEntiere
    };
  }
}
