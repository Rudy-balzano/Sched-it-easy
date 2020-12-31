package core;

import persist.FactoryDAOImpl;
import persist.MeetingDAO;
import persist.UserDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class ManagerFacade {

    private FactoryDAOImpl factoryDAO;
    private UserDAO userDAO;
    private MeetingDAO meetingDAO;

    public ManagerFacade(){
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.meetingDAO = factoryDAO.createMeetingDAO();
    }

    public boolean validateAccount(String username){

        return userDAO.validateAccount(username);

    }
    public boolean validationMeeting(Integer id){
        return meetingDAO.validateMeeting(id);
    }

    public Collection<String> getAllWaitingUsers(){
        return userDAO.findAllWaitingUsers();
    }
    public HashMap<String,Integer> getAllWaitingMeetings(){
        return meetingDAO.findAllWaitingMeetings();}
}
//decline account and decline meeting ??