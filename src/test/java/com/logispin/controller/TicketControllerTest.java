package com.logispin.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.logispin.model.Ticket;
import com.logispin.model.dto.TicketDTO;
import com.logispin.service.TicketService;

@RunWith(SpringRunner.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {
	
	private static final String JSON_REDEEMED_FALSE = "{" + "\"redeemed\"" + ":false}";
	private static final String JSON_REDEEMED_TRUE =  "{" + "\"redeemed\"" + ":true}";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TicketService ticketService;

	@MockBean
	private ModelMapper modelMapper;

	@Test
	public void redeemedIsFalseShouldReturnOK() throws Exception {
		given(ticketService.findById(Mockito.any())).willReturn(ticketRedeemedFalse());
		given(modelMapper.map(ticketRedeemedFalse().get(), TicketDTO.class)).willReturn(ticketDTORedeemedFalse().get());

		mockMvc.perform(get("/ticket/redeem/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(JSON_REDEEMED_FALSE));
	}
	
	@Test
	public void redeemedIsTrueShouldReturnGONE() throws Exception {
		given(ticketService.findById(Mockito.any())).willReturn(ticketRedeemedTrue());
		given(modelMapper.map(ticketRedeemedTrue().get(), TicketDTO.class)).willReturn(ticketDTORedeemedTrue().get());

		mockMvc.perform(get("/ticket/redeem/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isGone())
				.andExpect(content().string(JSON_REDEEMED_TRUE));
	}
	
	@Test
	public void testInvalidUrlShouldReturn404() throws Exception {
		mockMvc.perform(get("/ticket/redeem/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void redeemTicket() throws Exception {
		given(ticketService.redeem(Mockito.any())).willReturn(ticketRedeemedTrue());
		given(modelMapper.map(ticketRedeemedTrue().get(), TicketDTO.class)).willReturn(ticketDTORedeemedTrue().get());

		mockMvc.perform(post("/ticket/redeem/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(JSON_REDEEMED_TRUE));
	}
	
	private Optional<Ticket> ticketRedeemedFalse() {
		Ticket ticket = new Ticket();

		ticket.setRedeemed(false);

		return Optional.of(ticket);
	}

	private Optional<Ticket> ticketRedeemedTrue() {
		Ticket ticket = new Ticket();

		ticket.setRedeemed(true);

		return Optional.of(ticket);
	}

	private Optional<TicketDTO> ticketDTORedeemedFalse() {
		TicketDTO ticketDTO = new TicketDTO();

		ticketDTO.setRedeemed(false);

		return Optional.of(ticketDTO);
	}

	private Optional<TicketDTO> ticketDTORedeemedTrue() {
		TicketDTO ticketDTO = new TicketDTO();

		ticketDTO.setRedeemed(true);

		return Optional.of(ticketDTO);
	}

}
