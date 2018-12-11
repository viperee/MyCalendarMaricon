import {map} from 'rxjs/internal/operators';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Event } from '../models/event';
import { CalendarEvent } from 'calendar-utils';

const endpoint = 'http://localhost:3000/api/v1/';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  eventSubjetct = new Subject<any>();

  getAllEvents(): Observable<Event[]> {
    return null;
  }

  getEventById(id: number): Observable<Event> {
    return null;
  }

  createEvent(event: Event): Observable<Event> {
    return null;
  }

  updateEvent(event: Event): Observable<Event> {
    return null;
  }

  deleteEventById(id: number): Observable<Event> {
    return null;
  }

}
