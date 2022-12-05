package com.logispin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logispin.model.Ticket;
import com.logispin.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public void saveAll(List<Ticket> ticketList) {
		ticketRepository.saveAll(ticketList);
	}

	public Optional<Ticket> findById(Long id) {
		return ticketRepository.findById(id);
	}

	public Optional<Ticket> redeem(Long id) {
		final Optional<Ticket> ticketOpt = findById(id);
		if (ticketOpt.isPresent()) {
			Ticket ticket = ticketOpt.get();
			ticket.setRedeemed(true);
			final Ticket ticketDB = ticketRepository.save(ticket);
			return Optional.of(ticketDB);
		}
		return Optional.empty();
	}

}
