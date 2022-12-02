package com.logispin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logispin.model.dto.EventDTO;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@GetMapping("/redeem/{ticketId}")
	public ResponseEntity<List<EventDTO>> redeem(@PathVariable String ticketId) {
//		List<EventDTO> eventDTOList = modelMapper.map(eventService.findAll(), List.class);
//		return new ResponseEntity<List<EventDTO>>(eventDTOList, HttpStatus.OK);
		return null;
	}

}
