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
	
	public Event saveEvt(Event evt) {
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

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		
		// CHERCHER EN BDD
		try {
			Event event = getEventById(id);
			eventRepository.delete(event);
			return true;
		} catch (EventNotFoundException e) {
			return false;
		}
		
		
		// 1 TU LE TROUVES => TU SUPPRIMES => RETURN TRUE
		
		// 2 PAS TROUVE => TU FAIS RIEN => RETURN FALSE
		
		
		
	}

	public Event updateEvent(Event event, Long id) throws EventNotFoundException {
		// TODO Auto-generated method stub
		Event eventSaved = getEventById(id);
		if(!eventSaved.getTitre().equals(event.getTitre())) {
			eventSaved.setTitre(event.getTitre());
		}
		if(!eventSaved.getDateDebut().equals(event.getDateDebut())) {
			eventSaved.setDateDebut(event.getDateDebut());
		}
		if(!eventSaved.getDateFin().equals(event.getDateFin())) {
			eventSaved.setDateFin(event.getDateFin());
		}
		if(!eventSaved.getCouleurs().getPrimary().equals(event.getCouleurs().getPrimary())) {
			eventSaved.getCouleurs().setPrimary(event.getCouleurs().getPrimary());
		}
		if(!eventSaved.getCouleurs().getSecondary().equals(event.getCouleurs().getSecondary())) {
			eventSaved.getCouleurs().setSecondary(event.getCouleurs().getSecondary());
		}
		Event updatedEvent = saveEvt(eventSaved);
		return updatedEvent;
	}
	
}
