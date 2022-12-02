package com.logispin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logispin.model.Ticket;
import com.logispin.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;	
	
	public void saveAll(List<Ticket> ticketList) {
		ticketRepository.saveAll(ticketList);
	}
}
