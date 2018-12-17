package fr.mycalendarmaricon.mycalendarmaricon.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.mycalendarmaricon.mycalendarmaricon.exception.EventNotFoundException;
import fr.mycalendarmaricon.mycalendarmaricon.model.Event;
import fr.mycalendarmaricon.mycalendarmaricon.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("http://elasticbeanstalk-eu-west-3-002490453839.s3-website.eu-west-3.amazonaws.com")
@Api(value="Event", description="Operations pertaining to event in MyCalendar")
public class EventController {
	@Autowired
	private EventService eventService;
	
	public static final String URL_EVENTS = "/events";
	public static final String URL_EVENTS_WITH_ID = URL_EVENTS + "/{id}";
	
	@ApiOperation(value = "View a list of events")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
})
	@GetMapping(URL_EVENTS)
	public ResponseEntity<List<Event>> getAllEvents(){
		List<Event> res = eventService.getAllEvents();
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create an event")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Event created"),
})
	@PutMapping(URL_EVENTS)
	public ResponseEntity<Event> createEvent(@RequestBody Event evt){
		Event eventSaved = eventService.saveEvt(evt);
		return new ResponseEntity<>(eventSaved, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "View an event info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved event"),
            @ApiResponse(code = 404, message = "No event in db"),
})
	@GetMapping(URL_EVENTS_WITH_ID)
	public ResponseEntity<Event> getEventById(@PathVariable(name = "id") Long id){
		try {
			Event event = eventService.getEventById(id);
			return new ResponseEntity<>(event, HttpStatus.OK);
		} catch (EventNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Delete an event")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Event deleted"),
            @ApiResponse(code = 404, message = "Event not found in database"),
})
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
	@ApiOperation(value = "Update an event")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event updated"),
            @ApiResponse(code = 404, message = "Event not found in database"),
})
	@PostMapping(URL_EVENTS_WITH_ID)
	public ResponseEntity<Event> updateEventsById(@PathVariable(name = "id") Long id,@RequestBody Event event){
		//identifiant correct?
		//recup objet qu'on veut mettre à jour en bdd
		//regarder pour chaque champ si c pareil ou pas
		try {
			Event eventSaved = eventService.updateEvent(event,id);
			return new ResponseEntity<>(eventSaved, HttpStatus.OK);
		} catch (EventNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
