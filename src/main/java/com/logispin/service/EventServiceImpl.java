package com.logispin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logispin.model.Event;
import com.logispin.model.Ticket;
import com.logispin.model.dto.EventDTO;
import com.logispin.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Optional<Event> save(EventDTO eventDTO) {
		final List<Ticket> ticketList = new ArrayList<>(eventDTO.getInitialNumberOfTickets());
		eventDTO.setTicketList(ticketList);
		final Event event = modelMapper.map(eventDTO, Event.class);
		final Event eventDB = eventRepository.save(event);
		return Optional.of(eventDB);
	}

	@Override
	public Optional<Event> update(Long eventId, EventDTO eventDTO) {
		final Optional<Event> eventOpt = eventRepository.findById(eventId);
		
		if(eventOpt.isPresent()) {
			final List<Ticket> ticketList = new ArrayList<>(eventDTO.getInitialNumberOfTickets());
			eventDTO.setTicketList(ticketList);
			final Event event = modelMapper.map(eventDTO, Event.class);
			event.setId(eventId);
			final Event eventDB = eventRepository.save(event);
			return Optional.of(eventDB);
		} 
		return Optional.empty();
	}

	
}
