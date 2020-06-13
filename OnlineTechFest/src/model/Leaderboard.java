package model;

import java.util.Comparator;
import java.util.TreeSet;

public class Leaderboard {

	private TreeSet<Participant> rankList;		// in real world, list is going to be dynamic hence instead of list used TreeSet
	
	/*
	 * If the participants are same then don't add duplicates. Else compare score and
	 * update the ranks depending upon the scores. One with higher score will get higher
	 * rank (low int value)
	 * */
	
	private class scoreComparator implements Comparator<Participant> {
		@Override
		public int compare(Participant p1, Participant p2) {
			if(p1.equals(p2))
				return 0;
			if (p1.getScore() > p2.getScore()) {
				p2.setRank(p2.getRank() + 1);
				return -1;
			} else if (p1.getScore() < p2.getScore()) {
				p1.setRank(p1.getRank() + 1);
				return 1;
			} else {
				int maxRank = Math.max(p1.getRank(), p2.getRank());
				p1.setRank(maxRank);
				p2.setRank(maxRank);
				return 1;
			}
		}
	}

	public Leaderboard() {
		rankList = new TreeSet<Participant>(new scoreComparator());
	}

	public boolean addParticipant(Participant participant) {
		if(rankList.contains(participant))
			System.out.println("Yes");
		return this.rankList.add(participant);
	}

	public boolean removeParticipant(Participant participant) {
		return this.rankList.remove(participant);
	}

	public TreeSet<Participant> getList() {
		return rankList;
	}

	public void display() {
		if(rankList != null)
			for (Participant participant : rankList)
				System.out.println(participant);
		else
			System.err.println("None registered yet !");
	}
}