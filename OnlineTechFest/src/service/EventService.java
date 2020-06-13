package service;

import java.util.LinkedList;
import java.util.List;

import model.Event;
import model.Participant;

public class EventService {
	
	private List<Event> events;		//using in-memory db (mimicking db)
	
	public EventService() {
		events = new LinkedList<Event>();
	}
	
	public boolean addEvent(Event event) {
		return events.add(event);
	}
	
	public boolean removeEvent(Event event) {
		return events.remove(event);
	}
	
	public boolean registerParticipant(Event event, Participant participant) {
		return event.addParticipant(participant);
	}

	public boolean cancelParticipantRegistration(Event event, Participant participant) {
		return event.removeParticipant(participant);
	}
	
	public void showParticipants(Event event) {
		event.showParticipants();
	}
	
}