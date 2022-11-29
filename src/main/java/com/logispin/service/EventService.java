package com.logispin.service;

import java.util.Optional;

import com.logispin.model.Event;
import com.logispin.model.dto.EventDTO;

public interface EventService {

	Optional<Event> save(EventDTO eventDTO);

}
