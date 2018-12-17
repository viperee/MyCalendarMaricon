import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Event } from '../models/event';

const endpoint = 'http://maricon-env-1.pnam2iibwm.eu-west-3.elasticbeanstalk.com/events';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(endpoint);
  }

  getEventById(eventId: number): Observable<Event> {
    return this.http.get<Event>(endpoint + `/${eventId}`);
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.put<Event>(endpoint, event);
  }

  updateEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(endpoint + `/${event.id}`, event);
  }

  deleteEventById(eventId: number): Observable<Event> {
    return this.http.delete<Event>(endpoint + `/${eventId}`);
  }

}
