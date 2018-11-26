package fr.mycalendarmaricon.mycalendarmaricon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import fr.mycalendarmaricon.mycalendarmaricon.exception.EventNotFoundException;
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
	@Spy
	private EventService eventService;

	@Test
	public void testGetAllEvents() throws Exception {
		// WHEN
		Mockito.when(eventRepository.findAll()).thenReturn(new ArrayList<>());
		
		// GIVEN (Appel de la methode a tester)
		List<Event> allEvents = eventService.getAllEvents();
		
		// THEN (Assertions)
		assertThat(allEvents).isNotNull();
		
		Mockito.verify(eventRepository).findAll();
	
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
		
		Mockito.verify(eventRepository).save(Mockito.any(Event.class));
	}

	@Test
	public void testGetEventById() throws Exception {
		// WHEN 
		Long id = new Long(1);
		Event event = new Event();
		event.setId(id);
		Mockito.when(eventRepository.findById(Mockito.eq(id))).thenReturn(Optional.of(event));
		
		// GIVEN
		Event eventById = eventService.getEventById(id);
		
		//THEN
		assertThat(eventById.getId()).isEqualTo(event.getId());
		Mockito.verify(eventRepository).findById(Mockito.eq(id));
	}
	
	@Test(expected=EventNotFoundException.class)
	public void testGetEventByIdEmpty() throws Exception {
		// WHEN
		Long id = new Long(1);
		Mockito.when(eventRepository.findById(Mockito.eq(id))).thenReturn(Optional.empty());
		
		
		// GIVEN
		Event eventById = eventService.getEventById(id);
		
		// THEN
		Mockito.verify(eventRepository).findById(Mockito.eq(id));
		
	}

	@Test
	public void testDeleteByIdIsDeleted() throws Exception {
		// WHEN
		Long id = new Long(1);
		Event event = new Event();
		event.setId(id);
		Mockito.doReturn(event).when(eventService).getEventById(Mockito.eq(id));
		Mockito.doNothing().when(eventRepository).delete(Mockito.eq(event));
		
		// GIVEN
		boolean deleteById = eventService.deleteById(id);
		
		// THEN
		assertThat(deleteById).isTrue();
		Mockito.verify(eventService).getEventById(Mockito.eq(id));
		Mockito.verify(eventRepository).delete(Mockito.eq(event));
		
		
	}
	
	@Test
	public void testDeleteByIdIsNotDeleted() throws Exception {
		// WHEN
		Long id = new Long(1);
		Event event = new Event();
		event.setId(id);
		Mockito.doThrow(EventNotFoundException.class).when(eventService).getEventById(Mockito.eq(id));

		// GIVEN
		boolean deleteById = eventService.deleteById(id);
		
		// THEN
		assertThat(deleteById).isFalse();
		Mockito.verify(eventService).getEventById(Mockito.eq(id));
	}

}
