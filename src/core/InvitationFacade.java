package core;

import javafx.beans.property.StringProperty;
import persist.GroupDAO;
import persist.InvitationDAO;
import persist.UserDAO;
import persist.FactoryDAOImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class that represents a facade for an invitation
 * @author emilie jean
 * @version 1.0
 * @see gui.controllers.InvitationController
 * @see gui.controllers.InvitePeopleController
 * @see gui.controllers.SeeInvitedPeopleController
 */
public class InvitationFacade {
    /**
     * userDAO
     */
    private final UserDAO userDAO;
    /**
     * invitationDAO
     */
    private final InvitationDAO invitationDAO;
    /**
     * groupDAO
     */
    private final GroupDAO groupDAO;
    /**
     * Represents the username of the connected user
     */
    private String u;

    /**
     * Constructor of InvitationFacade
     * Use of FactoryDAO
     * @see SessionFacade
     */
    public InvitationFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.invitationDAO = factoryDAO.createInvitationDAO();
        this.groupDAO = factoryDAO.createGroupDAO();
        this.u = SessionFacade.getConnectedUser().getUserName();
    }

    /**
     * Function used to create a new invitation, returns a boolean
     * @param invitedUsername
     * @param state
     * @param idMeeting
     * @return True if the invitation has been created, false if not.
     */
    public boolean createInvitation (String invitedUsername, int state, int idMeeting){
        return invitationDAO.insert(invitedUsername, state, idMeeting);
    }

    /**
     * Display all the invitations for this username
     * @return an arrayList of all invitations for this username
     */
    public ArrayList<Invitation> getAllInvitation(){
        return invitationDAO.findAllInvitation(u);
    }

    /**
     * Function used to decline an invitation, say that we will be absent at the invitation's meeting.
     * @param username
     * @param id
     */
    public void declineInvitation(String username, int id){
        userDAO.declineWaitingInvitation(username, id);
    }

    /**
     * Function used to accept an invitation, say that we will be present at the invitation's meeting.
     * @param username
     * @param id
     */
    public void acceptInvitation(String username, int id){
        userDAO.acceptWaitingInvitation(username, id );
    }

    /**
     * Used to know all the invited users for a meeting
     * @param meetingId
     * @return all the usernames of the invited users for a meeting
     */
    public Collection<String> getAllInvitedUsers(int meetingId){
        return invitationDAO.getInvitedUsers(meetingId);
    }

    /**
     * return every users
     * @return every usernames of every users
     */
    public ArrayList<String> getAllUsers() {
        ArrayList<String> listUsers = new ArrayList<>();
        listUsers.addAll(userDAO.findAllRegUsersNames());
        listUsers.addAll(userDAO.findAllManagersNames());
        return listUsers;
    }

    /**
     * return all the groups invited
     * @param idMeeting
     * @return the name of every group invited
     */
    public ArrayList<String> getAllInvitedGroups(int idMeeting){
        return new ArrayList<>();
    }

    /**
     * return all the groups existing
     * @return every names of every groups existing
     */
    public ArrayList<String> getAllGroups(){
        ArrayList<String> listGroups = new ArrayList<>();
        listGroups.addAll(groupDAO.findAll());
        return listGroups;
    }
}
