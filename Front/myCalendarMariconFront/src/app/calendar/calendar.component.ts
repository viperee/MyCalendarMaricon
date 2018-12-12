import { MappingEventService } from './../services/mapping-event.service';
import { Event } from "./../models/event";
import { EventService } from "./../services/event.service";
import {
  Component,
  ChangeDetectionStrategy,
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
import { timer } from "rxjs";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView
} from "angular-calendar";
import { Couleur } from "../models/couleur";

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

  modalData: {
    action: string;
    event: CalendarEvent;
  };

  actions: CalendarEventAction[] = [];

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;

  constructor(private modal: NgbModal, private eventService: EventService, private mappingEventService: MappingEventService) {}

  ngOnInit(): void {
      this.eventService.getAllEvents().subscribe(events => {
        this.events = events.map(function(event) {
          console.log(this.event);
          return {
            id: this.event.id,
            start: new Date(this.event.dateDebut),
            end: new Date(this.event.dateFin),
            title: this.event.titre,
            color:  {
              primary: this.event.couleurs.primary,
              secondary:this.event.couleurs.secondary
            },
            actions: [
              {
                label: '<i class="fas fa-pencil-alt"></i>',
                onClick: ({ event }: { event: CalendarEvent }): void => {
                  this.handleEvent("Edited", event);
                }
              },
              {
                label: '<i class="fas fa-times"></i>',
                onClick: ({ event }: { event: CalendarEvent }): void => {
                  this.events = this.events.filter(iEvent => iEvent !== event);
                  this.eventService.deleteEventById(Number(event.id)).subscribe((eventDeletedInDb) =>{
                  });
                  console.log(event.id);
                  this.handleEvent("Deleted", event);
                }
              }
            ],
            resizable: {
              beforeStart: true,
              afterEnd: true
            },
            draggable: true,
            allDay: event._journeeEntiere
          };
      });
    });
    this.refresh.next();
    console.log(this.events);
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
    this.handleEvent("Dropped or resized", event);
    this.refresh.next();
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: "lg" });
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
    console.log(calendarEventToSave);
    let eventToSave: Event = this.mappingEventService.convertCalendarEventToEvent(calendarEventToSave);
    console.log(eventToSave);
    this.eventService.createEvent(eventToSave).subscribe((event) => {
      console.log(event);
      eventToSave = event;
    });
    calendarEventToSave.id = eventToSave._id;
    console.log(eventToSave);
    console.log(calendarEventToSave);
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
