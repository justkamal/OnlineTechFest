package ui;

import java.util.Date;
import java.util.LinkedList;

import model.Competition;
import model.Event;
import model.Participant;
import model.Student;
import model.Team;
import model.Workshop;
import service.EventService;

public class Driver {

	private static EventService eventService;

	public static void main(String[] args) throws Exception {

		Participant p1 = new Student.Builder().setName("Kamal").setCollege("V.E.S.I.T").build();

		Participant p2 = new Student.Builder().setName("Sarup").setCollege("V.J.T.I").build();

		Participant p3 = new Student.Builder().setName("Aashish").setCollege("TSEC").build();

		Participant team = new Team.Builder().add(new LinkedList<Student>() {
			{
				add(new Student.Builder().setName("Yogesh").setCollege("V.E.S.I.T").build());
				add(new Student.Builder().setName("Piyush").setCollege("V.E.S.I.T").build());
				add(new Student.Builder().setName("Vinay").setCollege("V.E.S.I.T").build());
				add(new Student.Builder().setName("Sagar").setCollege("V.E.S.I.T").build());
			}
		}).setName("CodeNation").build();

		Event soloEvent = new Competition.Builder().setPrizePool(15000).setType(Competition.Type.SOLO).setTitle("Programming")
				.setRegistrationFees(1300).setDuration(3).setDate(new Date()).build();

		Event teamEvent = new Competition.Builder().setPrizePool(2000000).setType(Competition.Type.TEAM).setTitle("Hackathon")
				.setRegistrationFees(2400).setDuration(24).setDate(new Date()).build();

		Event workshop = new Workshop.Builder().setGuestName("Kennedy").setTitle("Android").setRegistrationFees(4000).setDuration(4)
				.setDate(new Date()).build();

		eventService = new EventService();
		eventService.addEvent(soloEvent);
		eventService.addEvent(teamEvent);

		eventService.registerParticipant(soloEvent, p1);
		eventService.registerParticipant(soloEvent, p2);
		eventService.registerParticipant(soloEvent, p3);

		eventService.registerParticipant(teamEvent, team);
		soloEvent.showParticipants();
		teamEvent.showParticipants();
	}

}