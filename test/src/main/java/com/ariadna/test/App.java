package com.ariadna.test;

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ariadna.test.model.Event;
import com.ariadna.test.model.Source;
import com.ariadna.test.repositories.EventRepository;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private EventRepository eventRepository;

	private static final String[] FILE_HEADER_MAPPING = { "event_id", "fuente_id", "fuente_nombre", "timestamp",
			"valor" };

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println("--- importing csv file to mongo database ---");
		importAriadnaEvents();

		callToApi();
	}

	public void importAriadnaEvents() {
		ClassLoader classLoader = App.class.getClassLoader();
		File file = new File(classLoader.getResource("events.csv").getFile());

		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		try {
			fileReader = new FileReader(file);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = (CSVRecord) csvRecords.get(i);
				Source source = new Source(Long.parseLong(record.get("fuente_id")),
						(String) record.get("fuente_nombre"));
				Long time = Long.parseLong(record.get("timestamp"));
				Event event = new Event(Long.parseLong(record.get("event_id")), source, new Date(time),
						Long.parseLong(record.get("valor")));

				eventRepository.save(event);
			}
		} catch (Exception ex) {
			System.out.println("Exception parsing the csv file: " + ex.getMessage());
		}

	}

	public static void callToApi() {
		System.out.println("Calling to getEventsBySourceId method for source id 1 :");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Event[]>  responseEntity  = restTemplate.getForEntity("http://localhost:8080/events/source/1", Event[].class);
		Event[] events = responseEntity.getBody();
		printEvents(events);
		
		System.out.println("Calling to getEventsBySourceId method for source id 2 :");
		restTemplate = new RestTemplate();
		responseEntity  = restTemplate.getForEntity("http://localhost:8080/events/source/2", Event[].class);
		events = responseEntity.getBody();
		printEvents(events);
		
		System.out.println("---------------------------");
		
		System.out.println("Calling to getEventsByValues method for values between 10 and 12:");
		restTemplate = new RestTemplate();
		responseEntity  = restTemplate.getForEntity("http://localhost:8080/events/values/10/12", Event[].class);
		events = responseEntity.getBody();
		printEvents(events);
		
		System.out.println("Calling to getEventsByValues method for values between 10 and 50:");
		restTemplate = new RestTemplate();
		responseEntity  = restTemplate.getForEntity("http://localhost:8080/events/values/10/50", Event[].class);
		events = responseEntity.getBody();
		printEvents(events);
	}
	
	public static void printEvents(Event[] events){
		for (int i =0;i<events.length;i++){
			System.out.println("Event id: " + events[i].getEvent_id());
			System.out.println("Event date: " + events[i].getTimestamp());
			System.out.println("Event value: " + events[i].getValue());
		}
	}
} 
