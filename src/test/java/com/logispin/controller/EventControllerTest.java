package com.logispin.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.logispin.model.Event;
import com.logispin.model.dto.EventDTO;
import com.logispin.service.EventService;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EventService eventService;

	@MockBean
	private ModelMapper modelMapper;
	
	@Test
	public void getAllShouldReturnOK() throws Exception {
		given(eventService.findAll()).willReturn(new ArrayList<>());

		mockMvc.perform(get("/event/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void saveShouldReturnCreated() throws Exception {
		given(eventService.save(Mockito.any())).willReturn(event1());
		given(modelMapper.map(event1().get(), EventDTO.class)).willReturn(eventDTO1().get());

		mockMvc.perform(post("/event/")
				.content(eventDTO1JSON())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	private Optional<Event> event1() {
		Event event = new Event();
		event.setDate(new Date());
		event.setName("event1");
		event.setInitialNumberOfTickets(100);
		event.setTicketList(new ArrayList<>());
		return Optional.of(event);
	}
	
	private Optional<EventDTO> eventDTO1() {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setDate(new Date());
		eventDTO.setName("event1");
		eventDTO.setInitialNumberOfTickets(100);
		eventDTO.setTicketList(new ArrayList<>());
		return Optional.of(eventDTO);
	}
	
	private String eventDTO1JSON() {
    	return "{" + "\"name\"" + " : " +  "\"event1\"" + ", "
    			+ "\"initialNumberOfTickets\"" + " : 100 }";
    }

}
