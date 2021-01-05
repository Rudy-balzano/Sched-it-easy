package core;

import persist.FactoryDAOImpl;
import persist.MeetingDAO;
import persist.InvitationDAO;
import core.SessionFacade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public boolean createMeeting(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String meetingTopic){
        User creator = SessionFacade.getConnectedUser();
        String creatorUsername = creator.getUserName();
        boolean check = false;
        if (creator.getIsManager()){
            check = meetingDAO.insert(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }
        else {
            check = meetingDAO.insertWaitingMeeting(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }

        return check;
    }

    public int createMeetingAndGetId(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String meetingTopic){
        User creator = SessionFacade.getConnectedUser();
        String creatorUsername = creator.getUserName();
        int idMeeting = -1;
        if (creator.getIsManager()){
            idMeeting = meetingDAO.insertAndGetId(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }
        else {
            idMeeting = meetingDAO.insertWaitingMeetingAndGetId(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }

        return idMeeting;
    }

    public Meeting findMeetingById(int id){
        return meetingDAO.findByID(id);
    }




}
