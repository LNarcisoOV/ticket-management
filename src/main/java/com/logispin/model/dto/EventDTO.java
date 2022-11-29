package com.logispin.model.dto;

import java.util.Date;

public class EventDTO {

	private String name;

	private Date date;

	private Integer numberOfTickets;

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

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", date=" + date + ", numberOfTickets=" + numberOfTickets + "]";
	}
}
