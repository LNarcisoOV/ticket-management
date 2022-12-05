package com.logispin.model.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.logispin.model.Ticket;

public class EventDTO {

	private String name;

	private Date date;

	private Integer initialNumberOfTickets;

	private List<TicketDTO> ticketList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getInitialNumberOfTickets() {
		return initialNumberOfTickets;
	}

	public void setInitialNumberOfTickets(Integer initialNumberOfTickets) {
		this.initialNumberOfTickets = initialNumberOfTickets;
	}

	public List<TicketDTO> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<TicketDTO> ticketList) {
		this.ticketList = ticketList;
	}

	public Integer getCurrentTicketQuantity() {
		return this.ticketList != null && !this.ticketList.isEmpty() ? this.ticketList.size() : 0;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", date=" + date + ", initialNumberOfTickets=" + initialNumberOfTickets
				+ ", ticketList=" + ticketList + "]";
	}
}
