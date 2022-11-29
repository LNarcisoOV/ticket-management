package com.logispin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logispin.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
