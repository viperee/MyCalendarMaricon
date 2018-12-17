import { MappingEventService } from './../services/mapping-event.service';
import { Event } from "./../models/event";
import { EventService } from "./../services/event.service";
import {
  Component,
  ViewChild,
  TemplateRef,
  OnInit
} from "@angular/core";
import Swal from 'sweetalert2';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours
} from "date-fns";
import { Subject } from "rxjs";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView
} from "angular-calendar";


const colors: any = {
  red: {
    primary: "#ad2121",
    secondary: "#FAE3E3"
  },
  blue: {
    primary: "#1e90ff",
    secondary: "#D1E8FF"
  },
  yellow: {
    primary: "#e3bc08",
    secondary: "#FDF1BA"
  }
};

@Component({
  selector: "app-calendar",
  templateUrl: "./calendar.component.html",
  styleUrls: ["./calendar.component.css"]
})
export class CalendarComponent implements OnInit{
  @ViewChild("modalContent")
  modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  actions: CalendarEventAction[] = [];

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;

  constructor(private modal: NgbModal, private eventService: EventService, private mappingEventService: MappingEventService) {}

  ngOnInit(): void {
      this.eventService.getAllEvents().subscribe(events => {
        this.events = events.map(function(event) {
          return {
            id: event.id,
            start: new Date(event.dateDebut),
            end: new Date(event.dateFin),
            title: event.titre,
            color:  {
              primary: event.couleurs.primary,
              secondary: event.couleurs.secondary
            },
            actions: [
            ],
            resizable: {
              beforeStart: true,
              afterEnd: true
            },
            draggable: true,
            allDay: event.journeeEntiere
          };
      });
    });
    this.refresh.next();
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd
  }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    const eventToUpdate: Event = this.mappingEventService.convertCalendarEventToEvent(event);
    this.eventService.updateEvent(eventToUpdate).subscribe((eventUpdated) => {
      event = this.mappingEventService.convertEventToCalendarEvent(eventUpdated);
    })
    Swal(
      'Déplacement !',
      'L\'évènement a été déplacé avec succès !',
      'success'
    );
    this.refresh.next();
  }

  addEvent(): void {
    const calendarEventToSave: CalendarEvent = {
      title: "Nouvelle évènement",
      start: startOfDay(new Date()),
      end: endOfDay(new Date()),
      color: colors.red,
      draggable: true,
      resizable: {
        beforeStart: true,
        afterEnd: true
      }
    };
    let eventToSave: Event = this.mappingEventService.convertCalendarEventToEvent(calendarEventToSave);
    this.eventService.createEvent(eventToSave).subscribe((event) => {
      eventToSave = event;
      calendarEventToSave.id = eventToSave.id;
      this.events.push(calendarEventToSave);
      this.refresh.next();
    });


  }

  deleteById(index: number, eventId: number){
    this.eventService.deleteEventById(Number(eventId)).subscribe((eventDeletedInDb) =>{
      // Verifier status
      this.events.splice(index, 1);
      Swal(
        'Suppression !',
        'L\'évènement a été supprimé avec succès !',
        'success'
      );
    });
  }

  updateEvent(event: CalendarEvent){
    const eventToUpdate: Event = this.mappingEventService.convertCalendarEventToEvent(event);
    this.eventService.updateEvent(eventToUpdate).subscribe((eventUpdated) => {
      event = this.mappingEventService.convertEventToCalendarEvent(eventUpdated);
      Swal(
        'Modification !',
        'L\'évènement a été modifié avec succès !',
        'success'
      );
    });
  }
}
