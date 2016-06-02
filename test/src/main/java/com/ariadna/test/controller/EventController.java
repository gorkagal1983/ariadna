package com.ariadna.test.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ariadna.test.model.Event;
import com.ariadna.test.model.Source;
import com.ariadna.test.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventService eventService;
	
	@RequestMapping(method=RequestMethod.GET, value="/source/{sourceId}")
    public ResponseEntity<List<Event>> getEventsBySourceId(@PathVariable Long sourceId) {
        Source source = new Source(sourceId);
        List<Event> events = eventService.getEventsBySource(source);
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/values/{minValue}/{maxValue}")
    public ResponseEntity<List<Event>> getEventsByValues(@PathVariable Long minValue, @PathVariable Long maxValue) {
		 List<Event> events = eventService.getEventsByValues(minValue, maxValue);
		 return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/dates")
    public ResponseEntity<List<Event>> getEventsByDates(@RequestBody List<Date> dates) {
		List<Event> events =  eventService.getEventsByDates(dates);
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }
	
}
