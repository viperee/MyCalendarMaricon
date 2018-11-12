package fr.mycalendarmaricon.mycalendarmaricon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mycalendarmaricon.mycalendarmaricon.exception.EventNotFoundException;
import fr.mycalendarmaricon.mycalendarmaricon.model.Event;
import fr.mycalendarmaricon.mycalendarmaricon.repository.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}
	
	public Event createEvt(Event evt) {
		return eventRepository.save(evt);
	}

	public Event getEventById(Long id) throws EventNotFoundException {
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if(optionalEvent.isPresent()) {
			return optionalEvent.get();
		}
		else {
			throw new EventNotFoundException("L'evenement qui a pour identifiant" + id + " n'existe pas en bdd");
		}
	}
	
}
