package com.logispin.service;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Autowired
	private TicketService ticketService;
	
	@Override
	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Optional<Event> save(EventDTO eventDTO) {
		final Event event = modelMapper.map(eventDTO, Event.class);
		final Event eventDB = eventRepository.save(event);
		
		createTicketList(eventDB, eventDB.getInitialNumberOfTickets().longValue());
		
		return Optional.of(eventDB);
	}

	private void createTicketList(Event eventDB, Long ticketQuantity) {
		final List<Ticket> ticketList = new ArrayList<>();
		for(Ticket ticket : Arrays.asList(new Ticket[ticketQuantity.intValue()])) {
			ticket = new Ticket();
			ticket.setEvent(eventDB);
			ticket.setRedeemed(false);
			ticketList.add(ticket);
		}
		ticketService.saveAll(ticketList);
	}

	@Override
	public Optional<Event> addTicket(Long eventId, Long ticketQuantity) {
		final Optional<Event> eventOPT = eventRepository.findById(eventId);
		
		if(eventOPT.isPresent()) {
			Event eventDB = eventOPT.get();
			createTicketList(eventDB, ticketQuantity);
			return Optional.of(eventDB);
		}
		
		return Optional.empty();
	}

	
}
