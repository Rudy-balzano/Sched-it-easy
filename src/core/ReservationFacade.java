package core;

import persist.FactoryDAOImpl;
import persist.MeetingDAO;
import persist.InvitationDAO;
import core.SessionFacade;

import java.time.LocalDate;
import java.util.Date;

public class ReservationFacade {

    private User connectedUser;
    private FactoryDAOImpl factoryDAO;
    private MeetingDAO meetingDAO;

    /**
     * Default constructor
     */
    public ReservationFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.meetingDAO = factoryDAO.createMeetingDAO();
    }

    public boolean createMeeting(String topic, LocalDate date, String time, String duration){
        User creator = SessionFacade.getConnectedUser();
        String creatorUsername = creator.getUserName();
        return meetingDAO.insert(topic,date,time,duration,creatorUsername);
    }


}
