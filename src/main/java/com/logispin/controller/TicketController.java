package com.logispin.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logispin.model.Ticket;
import com.logispin.model.dto.TicketDTO;
import com.logispin.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/redeem/{ticketId}")
	public ResponseEntity<TicketDTO> redeemed(@PathVariable String ticketId) {
		try {
			final Optional<Ticket> ticketOpt = ticketService.findById(Long.parseLong(ticketId));
			if (ticketOpt.isPresent()) {

				LOGGER.info("Is ticket redeemed: {} ", ticketOpt.get().toString());
				final TicketDTO ticketDTOResponse = modelMapper.map(ticketOpt.get(), TicketDTO.class);

				if (ticketOpt.get().getRedeemed()) {
					return new ResponseEntity<TicketDTO>(ticketDTOResponse, HttpStatus.OK);
				} else {
					return new ResponseEntity<TicketDTO>(ticketDTOResponse, HttpStatus.GONE);
				}

			} else {
				LOGGER.warn("Error to get redeemed ticket.");
				return new ResponseEntity<TicketDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (RuntimeException runtimeException) {
			LOGGER.error("Error to get redeemed ticket: {} ", runtimeException.getMessage());
			throw new RuntimeException("An exception occurs;");
		}
	}

	@PostMapping("/redeem/{ticketId}")
	public ResponseEntity<TicketDTO> redeem(@PathVariable String ticketId) {
		try {
			final Optional<Ticket> ticketOpt = ticketService.redeem(Long.parseLong(ticketId));
			if (ticketOpt.isPresent()) {
				final TicketDTO ticketDTOResponse = modelMapper.map(ticketOpt.get(), TicketDTO.class);
				return new ResponseEntity<TicketDTO>(ticketDTOResponse, HttpStatus.OK);
			} else {
				LOGGER.warn("Error to get redeem ticket.");
				return new ResponseEntity<TicketDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (RuntimeException runtimeException) {
			LOGGER.error("Error redeem ticket: {} ", runtimeException.getMessage());
			throw new RuntimeException("An exception occurs;");
		}
	}

}
