package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import exception.ObjectBuildException;

public abstract class Event {
	
	private List<Participant> registeredParticipants;
	private String title;
	private int registrationFees;
	private Date date;
	private int duration; // hours

	public Event(Builder eventBuilder) {
		registeredParticipants = new LinkedList<Participant>();
		title = eventBuilder.title;
		registrationFees = eventBuilder.registrationFees;
		date = eventBuilder.date;
		duration = eventBuilder.duration;
	}

	public static abstract class Builder {

		private String title;
		private int registrationFees;
		private Date date;
		private int duration; 

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setRegistrationFees(int registrationFees) {
			this.registrationFees = registrationFees;
			return this;
		}

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setDuration(int duration) {
			this.duration = duration;
			return this;
		}

		public abstract Event build() throws ObjectBuildException;
	}
	
	public abstract void showParticipants();
	
	public String getTitle() {
		return title;
	}

	public int getRegistrationFees() {
		return registrationFees;
	}

	public Date getDate() {
		return date;
	}

	public int getDuration() {
		return duration;
	}

	public boolean addParticipant(Participant participant) {
		return registeredParticipants.add(participant);
	}

	public boolean removeParticipant(Participant participant) {
		return registeredParticipants.remove(participant);
	}
	
	public List<Participant> getRegisteredParticipants(){
		return registeredParticipants;
	}
}