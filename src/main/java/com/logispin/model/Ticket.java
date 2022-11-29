package com.logispin.model;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Event event;
	
	@Column
	private Boolean redeemed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Boolean getRedeemed() {
		return redeemed;
	}

	public void setRedeemed(Boolean redeemed) {
		this.redeemed = redeemed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(event, id, redeemed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(event, other.event) && Objects.equals(id, other.id)
				&& Objects.equals(redeemed, other.redeemed);
	}
}
