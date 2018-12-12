import { Event } from "./../models/event";
import { Couleur } from "./../models/couleur";
import { Injectable } from "@angular/core";
import { CalendarEvent } from "calendar-utils";

@Injectable({
  providedIn: "root"
})
export class MappingEventService {
  constructor() {}

  convertCalendarEventToEvent(calendarEvent: CalendarEvent): Event {
    const event = <Event>{};
    if (calendarEvent.id !== undefined) {
      event.id = Number(calendarEvent.id);
    }
    event.dateDebut = calendarEvent.start.toDateString();
    event.dateFin = calendarEvent.end.toDateString();
    event.titre = calendarEvent.title;
    event.couleurs = new Couleur(
      calendarEvent.color.primary,
      calendarEvent.color.secondary
    );
    event.journeeEntiere = calendarEvent.allDay;

    return event;
  }

  convertEventToCalendarEvent(event: Event) {
    return {
      id: event.id,
      title: event.titre,
      start: new Date(event.dateDebut),
      end: new Date(event.dateFin),
      color: {
        primary: event.couleurs.primary,
        secondary: event.couleurs.secondary
      },
      draggable: true,
      resizable: {
        beforeStart: true,
        afterEnd: true
      },
      alldays: event.journeeEntiere
    };
  }
}
