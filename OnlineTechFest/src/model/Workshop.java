package model;

import java.util.List;

import exception.ObjectBuildException;

public class Workshop extends Event{
	
	private List<Participant> registeredParticipants;
	private final String guestName;
	
	public Workshop(Builder builder) {
		super(builder);
		guestName = builder.guestName;
	}
	
	public static class Builder extends Event.Builder{
		private String guestName;
		
		public Builder setGuestName(String guestName) {
			this.guestName = guestName;
			return this;
		}
		
		public void validateBuild() throws ObjectBuildException {
			if(guestName == null)
				throw new ObjectBuildException("Please provide name of guest lecturer.");
		}
		
		@Override
		public Workshop build() throws ObjectBuildException {
			validateBuild();
			return new Workshop(this);
		}
	}
	
	public String getGuestName() {
		return guestName;
	}

	public List<Participant> getRegisteredParticipants() {
		return registeredParticipants;
	}

	@Override
	public boolean addParticipant(Participant participant) {
		return super.getRegisteredParticipants().add(participant);
	}

	@Override
	public boolean removeParticipant(Participant participant) {
		return super.getRegisteredParticipants().remove(participant);
	}

	@Override
	public void showParticipants() {
		if(super.getRegisteredParticipants() != null)
			for (Participant participant : super.getRegisteredParticipants())
				System.out.println(participant);
		else
			System.err.println("None participants registered !");
	}

}