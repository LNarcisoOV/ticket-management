package com.logispin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logispin.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
