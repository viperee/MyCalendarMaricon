package fr.mycalendarmaricon.mycalendarmaricon.restcontroller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.mycalendarmaricon.mycalendarmaricon.exception.EventNotFoundException;
import fr.mycalendarmaricon.mycalendarmaricon.model.Colors;
import fr.mycalendarmaricon.mycalendarmaricon.model.Event;
import fr.mycalendarmaricon.mycalendarmaricon.service.EventService;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EventService eventService;
	
	@Test
	public void testGetAllEvents() throws Exception {
		// WHEN
		
		Event event = new Event();
		List<Event> listEvent = Arrays.asList(event);
		
		Mockito.when(eventService.getAllEvents()).thenReturn(listEvent);
		// GIVEN
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(EventController.URL_EVENTS));
		
		// THEN
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).getAllEvents();
	}

	@Test
	public void testCreateEvent() throws Exception {
		// WHEN
		Event evt = new Event();
		Event eventSaved = new Event();
		Mockito.when(eventService.saveEvt(Mockito.eq(evt))).thenReturn(eventSaved);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String eventJson = objectMapper.writeValueAsString(evt);
		
		// GIVEN
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(EventController.URL_EVENTS).content(eventJson).contentType(MediaType.APPLICATION_JSON_UTF8));
		
		// THEN
		result.andExpect(MockMvcResultMatchers.status().isCreated());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).saveEvt(Mockito.eq(evt));
	}

	@Test
	public void testGetEventByIdIsOK() throws Exception {
		// WHEN
		Long id = new Long(1);
		Event event = new Event();
		Mockito.when(eventService.getEventById(Mockito.eq(id))).thenReturn(event);
		
		// GIVEN
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(EventController.URL_EVENTS_WITH_ID, id));
		
		// THEN
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).getEventById(Mockito.eq(id));
	}
	
	@Test
	public void testGetEventByIdIsNotFound() throws Exception {
		// WHEN
		Long id = new Long(1);
		Mockito.when(eventService.getEventById(Mockito.eq(id))).thenThrow(EventNotFoundException.class);
		
		// GIVEN
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(EventController.URL_EVENTS_WITH_ID, id));
		
		// THEN
		result.andExpect(MockMvcResultMatchers.status().isNotFound());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).getEventById(Mockito.eq(id));
	}

	@Test
	public void testDeleteEventByIdIsDeleted() throws Exception {
		// WHEN
		Long id = new Long(1);
		boolean isDeleted = true;
		Mockito.when(eventService.deleteById(Mockito.eq(id))).thenReturn(isDeleted);
		
		// GIVEN
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete(EventController.URL_EVENTS_WITH_ID, id));
		
		// THEN
		result.andExpect(MockMvcResultMatchers.status().isNoContent());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).deleteById(Mockito.eq(id));
	}
	
	@Test
	public void testDeleteEventByIdIsNotDeleted() throws Exception {
		// WHEN
		Long id = new Long(1);
		boolean isDeleted = false;
		Mockito.when(eventService.deleteById(Mockito.eq(id))).thenReturn(isDeleted);
		
		// GIVEN
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete(EventController.URL_EVENTS_WITH_ID, id));
		
		// THEN
		result.andExpect(MockMvcResultMatchers.status().isNotFound());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).deleteById(Mockito.eq(id));
	}

	@Test
	public void testUpdateEventsById() throws Exception {
		// WHEN
		String titre = "test";
		String dateDebut = "4 dec";
		String dateFin = "5 dec";
		Colors couleursRouge = new Colors("#ad2121", "#FAE3E3");
		boolean journeeEntiere = true;
		Event eventWithData = new Event(titre, dateDebut, dateFin, couleursRouge, journeeEntiere);
		Long id = new Long(1);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String eventJson = objectMapper.writeValueAsString(eventWithData);
		
		Event eventUpdated = new Event(id,titre, dateDebut, dateFin, couleursRouge, journeeEntiere);
		Mockito.when(eventService.updateEvent(Mockito.any(Event.class), Mockito.eq(id))).thenReturn(eventUpdated);
		
		// GIVEN
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(EventController.URL_EVENTS_WITH_ID, id).content(eventJson).contentType(MediaType.APPLICATION_JSON_UTF8));
		
		
		// THEN
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).updateEvent(Mockito.any(Event.class), Mockito.eq(id));
	}
	
	@Test
	public void testUpdateEventsByIdEventNotFoundException() throws Exception {
		// WHEN
		String titre = "test";
		String dateDebut = "4 dec";
		String dateFin = "5 dec";
		Colors couleursRouge = new Colors("#ad2121", "#FAE3E3");
		Event eventWithData = new Event(titre, dateDebut, dateFin, couleursRouge, true);
		Long id = new Long(1);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String eventJson = objectMapper.writeValueAsString(eventWithData);
		
		Mockito.when(eventService.updateEvent(Mockito.any(Event.class), Mockito.eq(id))).thenThrow(EventNotFoundException.class);

		
		// GIVEN
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(EventController.URL_EVENTS_WITH_ID, id).content(eventJson).contentType(MediaType.APPLICATION_JSON_UTF8));
		
		
		// THEN
		result.andExpect(MockMvcResultMatchers.status().isNotFound());
		result.andDo(MockMvcResultHandlers.print());
		
		Mockito.verify(eventService).updateEvent(Mockito.any(Event.class), Mockito.eq(id));
	}

}
