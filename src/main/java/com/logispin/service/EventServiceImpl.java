package com.logispin.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logispin.model.Event;
import com.logispin.model.dto.EventDTO;
import com.logispin.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Optional<Event> save(EventDTO eventDTO) {
		final Event event = modelMapper.map(eventDTO, Event.class);
		final Event eventDB = eventRepository.save(event);
		return Optional.of(eventDB);
	}
}