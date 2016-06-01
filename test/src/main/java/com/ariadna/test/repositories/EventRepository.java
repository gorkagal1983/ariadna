package com.ariadna.test.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ariadna.test.model.Event;

@RepositoryRestResource(collectionResourceRel = "event", path = "events")
public interface EventRepository extends MongoRepository<Event, String>, EventRepositoryCustom{

	
}
