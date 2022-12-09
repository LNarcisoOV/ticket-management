package com.logispin.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logispin.model.Event;
import com.logispin.model.dto.EventDTO;
import com.logispin.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/")
	@SuppressWarnings("unchecked")
	public ResponseEntity<List<EventDTO>> getAll() {
		List<EventDTO> eventDTOList = modelMapper.map(eventService.findAll(), List.class);
		return new ResponseEntity<List<EventDTO>>(eventDTOList, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<EventDTO> save(@RequestBody EventDTO eventDTO) {
		try {
			final Optional<Event> eventOpt = eventService.save(eventDTO);
			if (eventOpt.isPresent()) {
				LOGGER.info("Created event: {} ", eventOpt.get().toString());
				final EventDTO eventDTOResponse = modelMapper.map(eventOpt.get(), EventDTO.class);
				return new ResponseEntity<EventDTO>(eventDTOResponse, HttpStatus.CREATED);
			} else {
				LOGGER.warn("Error to create event.");
				return new ResponseEntity<EventDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (RuntimeException runtimeException) {
			LOGGER.error("Error to create event: {} ", runtimeException.getMessage());
			throw new RuntimeException("An exception occurs;");
		}
	}
	
	@PutMapping("/{eventId}/add-ticket/{ticketQuantity}")
	public ResponseEntity<EventDTO> addTicket(@PathVariable String eventId, @PathVariable String ticketQuantity) {
		try {
			final Optional<Event> eventOpt = eventService.addTicket(Long.parseLong(eventId), Long.parseLong(ticketQuantity));
			if (eventOpt.isPresent()) {
				LOGGER.info("Added tickets to event: {} ", eventOpt.get().toString());
				final EventDTO eventDTOResponse = modelMapper.map(eventOpt.get(), EventDTO.class);
				return new ResponseEntity<EventDTO>(eventDTOResponse, HttpStatus.CREATED);
			} else {
				LOGGER.warn("Error to update event.");
				return new ResponseEntity<EventDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (RuntimeException runtimeException) {
			LOGGER.error("Error to add tickets to event: {} ", runtimeException.getMessage());
			throw new RuntimeException("An exception occurs;");
		}
	}
}
