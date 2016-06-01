package com.ariadna.test.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ariadna.test.model.Event;
import com.ariadna.test.model.Source;

public class EventRepositoryImpl implements EventRepositoryCustom{

	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	public List<Event> findBySource(Source source){
		List<Event> events = new ArrayList<Event>();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("source._id").is(source.getSource_id()));

		events = mongoTemplate.find(query, Event.class);
				
		return events;
	}
	
	@Override
	public List<Event> findByValueRange(Long minValue, Long maxValue){
		List<Event> events = new ArrayList<Event>();
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(Criteria.where("value").gt(minValue));
		criterias.add(Criteria.where("value").lt(maxValue));
		
		Criteria criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
		
		Query query = new Query();
		query.addCriteria(criteria);
		
		events = mongoTemplate.find(query, Event.class);
		
		return events;
	}
	
	@Override
	public List<Event> findByTimestamps(List<Date> dates){
		List<Event> events = new ArrayList<Event>();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("timestamp").in(dates));
		
		events = mongoTemplate.find(query, Event.class);
		
		return events;
	}

}
