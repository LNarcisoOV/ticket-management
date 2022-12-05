package com.logispin.service;

import java.util.List;
import java.util.Optional;

import com.logispin.model.Ticket;

public interface TicketService {

	public void saveAll(List<Ticket> ticketList);

	public Optional<Ticket> findById(Long id);
	
	public Optional<Ticket> redeem(Long id);
	
}
