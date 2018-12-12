import { MappingEventService } from './../services/mapping-event.service';
import { Event } from "./../models/event";
import { EventService } from "./../services/event.service";
import {
  Component,
  ViewChild,
  TemplateRef,
  OnInit
} from "@angular/core";
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
              {
                label: '<i class="fas fa-pencil-alt"></i>',
                onClick: ({ event }: { event: CalendarEvent }): void => {
                  alert("Edited");
                }
              },
              {
                label: '<i class="fas fa-times"></i>',
                onClick: ({ event }: { event: CalendarEvent }): void => {
                  //const eventToRemove: Event = this.mappingEventService.convertEventToCalendarEvent(event);
                  //events = events.filter(iEvent => iEvent !== eventToRemove);
                  //eventService.deleteEventById(Number(event.id)).subscribe((eventDeletedInDb) =>{
                  //});
                  alert("Deleted");
                }
              }
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
    alert("Dropped or resized");
    this.refresh.next();
  }

  addEvent(): void {
    const calendarEventToSave: CalendarEvent = {
      title: "New event",
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
    });
    calendarEventToSave.id = eventToSave.id;
    this.events.push(calendarEventToSave);
    this.refresh.next();
  }

  deleteById(index: number, eventId: number){
    this.eventService.deleteEventById(Number(eventId)).subscribe((eventDeletedInDb) =>{
      // Verifier status
      this.events.splice(index, 1);
    });
  }
}
