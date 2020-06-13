package model;

import exception.ObjectBuildException;

public class Student extends Participant{

	private final String name;
	
	public Student(Builder builder) throws ObjectBuildException {
		super(builder);
		this.name = builder.name;
		if(super.getName() == null || super.getCollege() == null)
			throw new ObjectBuildException("Please provide name and college");
	}
	
	//Builder pattern for object construction
	public static class Builder extends Participant.Builder{

		private String name;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setCollege(String college) {
			super.setCollege(college);
			return this;
		}
		
		@Override
		public long generateId() {
			long sum = 0;

			for (char ch : name.toCharArray())
				if (ch >= 'A' && ch <= 'Z')
					sum += ch - 'A';
				else if (ch >= 'a' && ch <= 'z')
					sum += ch - 'a';
			return sum;
		}
		
		public Student build() throws ObjectBuildException {
			super.setName(name);
			super.setId(generateId());				
			return new Student(this);
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", college=" + super.getCollege() + "]";
	}
}