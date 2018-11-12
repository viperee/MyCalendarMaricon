package fr.mycalendarmaricon.mycalendarmaricon.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/events")
	public ResponseEntity<List<Event>> getAllEvents(){
		List<Event> res = eventService.getAllEvents();
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/events")
	public ResponseEntity<Event> createEvent(@RequestBody Event evt){
		Event eventSaved = eventService.createEvt(evt);
		return new ResponseEntity<>(eventSaved, HttpStatus.OK);
	}
	
	@GetMapping("/events/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable(name = "id") Long id){
		try {
			Event event = eventService.getEventById(id);
			return new ResponseEntity<>(event, HttpStatus.OK);
		} catch (EventNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
