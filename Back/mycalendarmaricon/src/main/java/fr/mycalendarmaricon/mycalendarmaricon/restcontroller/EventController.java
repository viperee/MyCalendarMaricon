package fr.mycalendarmaricon.mycalendarmaricon.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.mycalendarmaricon.mycalendarmaricon.exception.EventNotFoundException;
import fr.mycalendarmaricon.mycalendarmaricon.model.Event;
import fr.mycalendarmaricon.mycalendarmaricon.service.EventService;

@RestController
public class EventController {
	@Autowired
	private EventService eventService;
	
	public static final String URL_EVENTS = "/events";
	public static final String URL_EVENTS_WITH_ID = URL_EVENTS + "/{id}";
	
	@GetMapping(URL_EVENTS)
	public ResponseEntity<List<Event>> getAllEvents(){
		List<Event> res = eventService.getAllEvents();
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping(URL_EVENTS)
	public ResponseEntity<Event> createEvent(@RequestBody Event evt){
		Event eventSaved = eventService.createEvt(evt);
		return new ResponseEntity<>(eventSaved, HttpStatus.CREATED);
	}
	
	@GetMapping(URL_EVENTS_WITH_ID)
	public ResponseEntity<Event> getEventById(@PathVariable(name = "id") Long id){
		try {
			Event event = eventService.getEventById(id);
			return new ResponseEntity<>(event, HttpStatus.OK);
		} catch (EventNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(URL_EVENTS_WITH_ID)
	public ResponseEntity<Event> deleteEventById(@PathVariable(name = "id") Long id){
		boolean isDeleted = eventService.deleteById(id);
		if(isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
	}
	
	// Mettre à jour un evenement par id
	
	// Supprimer tous les events de la base
	
}
