package core;

import persist.FactoryDAOImpl;
import persist.GroupDAO;
import persist.MeetingDAO;
import persist.UserDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class that represent the facade for a manager
 * @version 1.0
 * @see gui.controllers.AccountValidationController
 * @see gui.controllers.MeetingValidationController
 * @see gui.controllers.ManagerManageGroupController
 * @see gui.controllers.ManagerInfoGroupController
 * @see gui.controllers.ManagerAddUserToGroupController
 * @see gui.controllers.ManagerAddNewGroupController
 */

public class ManagerFacade {
    /**
     * userDAO
     */
    private final UserDAO userDAO;
    /**
     * meetingDAO
     */
    private final MeetingDAO meetingDAO;
    /**
     * groupDAO
     */
    private final GroupDAO groupDAO;

    /**
     * Constructor of manager facade
     * Use of factoryDAO
     */
    public ManagerFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.meetingDAO = factoryDAO.createMeetingDAO();
        this.groupDAO = factoryDAO.createGroupDAO();
    }

    /**
     * Function used to validate an account thanks to an username
     * @param username
     */
    public void validateAccount(String username){

        userDAO.validateAccount(username);
    }

    /**
     * Function used to validate a meeting
     * @param id
     */
    public void validationMeeting(Integer id){
        meetingDAO.validateMeeting(id);
    }

    /**
     * Function used to see all the users that haven't a valitated account yet.
     * @return a collection of every users that haven't a valitated account yet.
     */
    public Collection<String> getAllWaitingUsers(){

        return userDAO.findAllWaitingUsers();
    }

    /**
     * Function used to see all the meetings that haven't been validated yet.
     * @return a HashMap of every meetings that haven't been yet.
     */
    public HashMap<Integer,String> getAllWaitingMeetings(){

        return meetingDAO.findAllWaitingMeetings();
    }

    /**
     * Function used to see a meeting that haven't been validated yet thanks to his id.
     * @param id
     * @return a meeting that haven't been validated yet thanks to his id.
     */
    public Meeting getWaitingMeetingById(int id){
        return meetingDAO.findWaitingMeetingByID(id);
    }

    /**
     * Return every groups existing
     * @return a collection of every groups existing
     */
    public Collection<String> getAllGroups(){

        return groupDAO.findAll();
    }

    /**
     * Function used to delete a group thanks to his name
     * @param name
     */
    public void deleteGroup(String name){

        groupDAO.delete(name);
    }

    /**
     * Function used to find a group thanks to his name
     * @param name
     * @return the group that has been found
     */
    public Group findGroupByName(String name){

        return groupDAO.findByName(name);
    }

    /**
     * Function used to delete an user in a group
     * @param groupName
     * @param username
     */
    public void deleteFromGroup(String groupName, String username){

        groupDAO.deleteMember(username,groupName);
    }

    /**
     * Function used to add an user in a group
     * @param groupName
     * @param username
     */
    public void addToGroup(String groupName, String username){

        groupDAO.addMember(username,groupName);
    }

    /**
     * Function used to add a new group
     * @param name
     * @return True if the goup has been created, False if not.
     */
    public boolean addGroup(String name){

        return groupDAO.insert(name);
    }

    /**
     * Function used to decline an account, it's not validated
     * @param username
     */
    public void declineAccount(String username){
        userDAO.declineWaitingUser(username);
    }

    /**
     * Function used to decline a meeting, it's not validated
     * @param id
     */
    public void declineMeeting(Integer id){
        meetingDAO.declineWaitingMeeting(id);
    }

    /**
     * Return every users existing
     * @return an ArrayList of every users existing
     */
    public ArrayList<String> getAllUsers(){
        Collection<String> u = userDAO.findAllRegUsersNames();
        Collection<String> m = userDAO.findAllManagersNames();

        u.addAll(m);
        Collections.sort((ArrayList<String>) u);

        return (ArrayList<String>) u;
    }


}
