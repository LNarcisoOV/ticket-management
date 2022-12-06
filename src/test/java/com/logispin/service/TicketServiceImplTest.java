package com.logispin.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.logispin.model.Ticket;
import com.logispin.repository.TicketRepository;

@RunWith(SpringRunner.class)
public class TicketServiceImplTest {
	
	@InjectMocks
	private TicketServiceImpl ticketServiceImpl;
	
	@Mock
	private TicketRepository ticketRepository;

	@Test
	public void redeem() {
		when(ticketRepository.findById(1L)).thenReturn(ticketRedeemedFalse());
		when(ticketRepository.save(Mockito.any())).thenReturn(ticketRedeemedTrue().get());
		
		Optional<Ticket> ticketOpt = ticketServiceImpl.redeem(1L);
		assertTrue(ticketOpt.get().getRedeemed());
	}
	
	private Optional<Ticket> ticketRedeemedTrue() {
		Ticket ticket = new Ticket();

		ticket.setRedeemed(true);

		return Optional.of(ticket);
	}
	
	private Optional<Ticket> ticketRedeemedFalse() {
		Ticket ticket = new Ticket();

		ticket.setRedeemed(false);

		return Optional.of(ticket);
	}
}
