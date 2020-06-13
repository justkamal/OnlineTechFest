package model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import exception.ObjectBuildException;

public class Team extends Participant {

	private final List<Student> group;
	private final String name;

	public Team(Builder builder) {
		super(builder);
		this.name = builder.name;
		this.group = builder.group;
	}

	// Builder pattern for object construction
	public static class Builder extends Participant.Builder {

		private List<Student> group;
		private String name;

		public Builder() {
			group = new LinkedList<Student>();
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder add(List<Student> student) {
			this.group.addAll(student);
			return this;
		}
		
		public void validateBuild() throws ObjectBuildException {
			if(name == null || group.size() == 0)
				throw new ObjectBuildException("Insufficient input to create team. Add name and students");
			
			if(group.size() != 4)
				throw new ObjectBuildException("Team should be of 4 students");
			
			HashSet<String> unique = new HashSet<String>();
			for(Student student : group)
				unique.add(student.getCollege());
			if(unique.size() != 1)
				throw new ObjectBuildException("Students in team should be of same college");
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
		
		@Override
		public Team build() throws ObjectBuildException {
			super.setName(name);
			super.setId(generateId());
			
			validateBuild();
			return new Team(this);
		}
	}

	@Override
	public String toString() {
		return "Team [group=" + group + ", name=" + name + "]";
	}
}