package com.logispin.service;

import java.util.List;
import java.util.Optional;

import com.logispin.model.Event;
import com.logispin.model.dto.EventDTO;

public interface EventService {

	Optional<Event> save(EventDTO eventDTO);

	List<Event> findAll();

	Optional<Event> addTicket(Long eventId, Long ticketQuantity);

}
