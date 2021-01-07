package core;

import persist.FactoryDAOImpl;
import persist.GroupDAO;
import persist.MeetingDAO;
import persist.UserDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class ManagerFacade {

    private final UserDAO userDAO;
    private final MeetingDAO meetingDAO;
    private final GroupDAO groupDAO;

    public ManagerFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.meetingDAO = factoryDAO.createMeetingDAO();
        this.groupDAO = factoryDAO.createGroupDAO();
    }

    public void validateAccount(String username){

        userDAO.validateAccount(username);

    }
    public void validationMeeting(Integer id){

        meetingDAO.validateMeeting(id);
    }

    public Collection<String> getAllWaitingUsers(){

        return userDAO.findAllWaitingUsers();
    }

    public HashMap<Integer,String> getAllWaitingMeetings(){

        return meetingDAO.findAllWaitingMeetings();
    }

    public Meeting getWaitingMeetingById(int id){
        return meetingDAO.findWaitingMeetingByID(id);
    }

    public Collection<String> getAllGroups(){

        return groupDAO.findAll();
    }

    public void deleteGroup(String name){

        groupDAO.delete(name);
    }

    public Group findGroupByName(String name){

        return groupDAO.findByName(name);
    }

    public void deleteFromGroup(String groupName, String username){

        groupDAO.deleteMember(username,groupName);
    }

    public void addToGroup(String groupName, String username){

        groupDAO.addMember(username,groupName);
    }

    public boolean addGroup(String name){

        return groupDAO.insert(name);
    }
    public void declineAccount(String username){
        userDAO.declineWaitingUser(username);
    }
    public void declineMeeting(Integer id){
        meetingDAO.declineWaitingMeeting(id);
    }

    public ArrayList<String> getAllUsers(){
        Collection<String> u = userDAO.findAllRegUsersNames();
        Collection<String> m = userDAO.findAllManagersNames();

        u.addAll(m);
        Collections.sort((ArrayList<String>) u);

        return (ArrayList<String>) u;
    }


}
