package com.logispin.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column
	private Date date;

	@Column
	private Integer initialNumberOfTickets;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="EVENT_ID")
	private List<Ticket> ticketList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	public int hashCode() {
		return Objects.hash(date, id, initialNumberOfTickets, name, ticketList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Objects.equals(initialNumberOfTickets, other.initialNumberOfTickets)
				&& Objects.equals(name, other.name) && Objects.equals(ticketList, other.ticketList);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", date=" + date + ", initialNumberOfTickets="
				+ initialNumberOfTickets + ", ticketList=" + ticketList + "]";
	}
}
