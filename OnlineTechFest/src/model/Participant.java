package model;

import exception.ObjectBuildException;

public abstract class Participant{
	
	private final long id;
	private final String name;
	private final String college;
	private int rank;
	private long score;
	private double reward;

	public Participant(Builder builder) {
		this.name = builder.name;
		this.id = builder.id;
		this.college = builder.college;
		this.rank = builder.rank;
		this.score = builder.score;
		this.reward = builder.reward;
	}

	// Builder pattern for object construction
	public static abstract class Builder {
		
		private String name;
		private long id;
		private String college;
		private int rank;
		private long score;
		private double reward;
		
		public Builder() {
			this.rank = 1;
			this.score = 0;
		}
		
		protected void setId(long id) {
			this.id = id;
		}
		
		protected Builder setCollege(String college) {
			this.college = college;
			return this;
		}
		
		protected Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public abstract Participant build() throws ObjectBuildException;
		
		public abstract long generateId();

	}
	
	public long getId() {
		return id;
	}
	
	public String getCollege() {
		return college;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Participant [rank=" + rank + ", score=" + score + ", reward=" + reward + "]";
	}

	public String getName() {
		return name;
	}
}