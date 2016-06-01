package com.ariadna.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ariadna.test.model.Event;
import com.ariadna.test.model.Source;
import com.ariadna.test.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventService eventService;
	
	@RequestMapping(method=RequestMethod.GET, value="/source/{sourceId}")
    public @ResponseBody List<Event> getEventsBySourceId(@PathVariable Long sourceId) {
        Source source = new Source(sourceId);
		return eventService.getEventsBySource(source);
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/values/{minValue}/{maxValue}")
    public @ResponseBody List<Event> getEventsByValues(@PathVariable Long minValue, @PathVariable Long maxValue) {
		return eventService.getEventsByValues(minValue, maxValue);
    }
	
}
