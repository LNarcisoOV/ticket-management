package com.logispin.model.dto;

import java.util.Date;
import java.util.List;

import com.logispin.model.Ticket;

public class EventDTO {

	private String name;

	private Date date;

	private Integer initialNumberOfTickets;

	private List<Ticket> ticketList;

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

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", date=" + date + ", initialNumberOfTickets="
				+ initialNumberOfTickets + ", ticketList=" + ticketList + "]";
	}
}
