package fr.mycalendarmaricon.mycalendarmaricon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.mycalendarmaricon.mycalendarmaricon.model.Event;
import fr.mycalendarmaricon.mycalendarmaricon.repository.EventRepository;

@SuppressWarnings("deprecation")
// Barré car sera supprimé dans mockito 3
@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
	
	// Simule l'objet eventRepository n'appel pas la vrai bbd par exemple
	@Mock
	private EventRepository eventRepository;
	
	// 
	@InjectMocks
	private EventService eventService;

	@Test
	public void testGetAllEvents() throws Exception {
		// WHEN
		Mockito.when(eventRepository.findAll()).thenReturn(new ArrayList<>());
		
		// GIVEN (Appel de la methode a tester)
		List<Event> allEvents = eventService.getAllEvents();
		
		// THEN (Assertions)
		assertThat(allEvents).isNotNull();
	
	}

	@Test
	public void testCreateEvt() throws Exception {
		// WHEN
		Event evt = new Event();
		Event evtSaved = new Event();
		evtSaved.setId(1L);
		Mockito.when(eventRepository.save(Mockito.any(Event.class))).thenReturn(evtSaved);
		
		// GIVEN
		Event event = eventService.createEvt(evt);
		
		// THEN
		assertThat(event).isNotNull();
		assertThat(event.getId()).isEqualTo(evtSaved.getId());
	}

	@Test
	public void testGetEventById() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDeleteById() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
