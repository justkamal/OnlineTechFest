package model;

import java.util.TreeSet;

import exception.ObjectBuildException;

public class Competition extends Event{
	
	private Leaderboard leaderboard;
	private final int prizePool;
	private final Type type;

	public enum Type {
		SOLO, TEAM;
	}
	
	public Competition(Builder builder) {
		super(builder);
		this.leaderboard = builder.leaderboard;
		this.prizePool = builder.prizePool;
		this.type = builder.type;
	}
	
	public static class Builder extends Event.Builder{
		
		private Leaderboard leaderboard;
		private int prizePool;
		private Type type;
		
		public Builder setPrizePool(int prizePool) {
			this.prizePool = prizePool;
			return this;
		}
		
		public Builder setType(Type type) {
			this.type = type;
			return this;
		}
		
		public void validateBuild() throws ObjectBuildException {
			if(prizePool == 0 || type == null)
				throw new ObjectBuildException("Please provide proper prize pool amount and type of competiton.");
		}
		
		@Override
		public Competition build() throws ObjectBuildException {
			leaderboard = new Leaderboard();
			validateBuild();
			return new Competition(this);
		}
	}
	
	@Override
	public void showParticipants() {
		this.leaderboard.display();
	}
	
	public Leaderboard getLeaderboard() {
		return leaderboard;
	}

	public int getPrizePool() {
		return prizePool;
	}	

	public String getType() {
		return type.toString();
	}
	
	@Override
	public boolean addParticipant(Participant participant) {
		return this.leaderboard.addParticipant(participant) && super.addParticipant(participant);
	}

	@Override
	public boolean removeParticipant(Participant participant) {
		return this.leaderboard.removeParticipant(participant) && super.removeParticipant(participant);
	}
	
	//will be called when the event is over
	public void computeRewards() {
		TreeSet<Participant> participants = leaderboard.getList();
		if (participants.isEmpty())
			return;
		for (Participant participant : participants) {
			switch (participant.getRank()) {
			case 1:
				participant.setReward(prizePool * 0.5);
				break;
			case 2:
				participant.setReward(prizePool * 0.3);
				break;
			case 3:
				participant.setReward(prizePool * 0.2);
				break;
			default:
				participant.setReward(0);
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "Competition [leaderboard=" + leaderboard + ", prizePool=" + prizePool + ", type=" + type + "]";
	}

}