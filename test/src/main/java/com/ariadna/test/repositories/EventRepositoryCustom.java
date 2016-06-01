package com.ariadna.test.repositories;

import java.util.Date;
import java.util.List;

import com.ariadna.test.model.Event;
import com.ariadna.test.model.Source;

public interface EventRepositoryCustom {

	public List<Event> findBySource(Source source);
	public List<Event> findByValueRange(Long minValue, Long maxValue);
	public List<Event> findByTimestamps(List<Date> dates);

}
