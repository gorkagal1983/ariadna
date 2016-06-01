package com.ariadna.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ariadna.test.model.Event;
import com.ariadna.test.model.Source;
import com.ariadna.test.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	public List<Event> getEventsBySource(Source source){
		return eventRepository.findBySource(source);
	}
	
	public List<Event> getEventsByValues(Long minValue, Long maxValue){
		return eventRepository.findByValueRange(minValue, maxValue);
	}
	
}
