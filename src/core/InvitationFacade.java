package core;

import javafx.beans.property.StringProperty;
import persist.GroupDAO;
import persist.InvitationDAO;
import persist.UserDAO;
import persist.FactoryDAOImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InvitationFacade {
    private final UserDAO userDAO;
    private final InvitationDAO invitationDAO;
    private final GroupDAO groupDAO;
    private String u;


    public InvitationFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.invitationDAO = factoryDAO.createInvitationDAO();
        this.groupDAO = factoryDAO.createGroupDAO();
        this.u = SessionFacade.getConnectedUser().getUserName();
    }

    public boolean createInvitation (String invitedUsername, int state, int idMeeting){
        return invitationDAO.insert(invitedUsername, state, idMeeting);
    }

    public ArrayList<Invitation> getAllInvitation(){
        return invitationDAO.findAllInvitation(u);
    }
    public void declineInvitation(String username, int id){
        userDAO.declineWaitingInvitation(username, id);
    }
    public void acceptInvitation(String username, int id){
        userDAO.acceptWaitingInvitation(username, id );
    }

    public Collection<String> getAllInvitedUsers(int meetingId){
        return invitationDAO.getInvitedUsers(meetingId);
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> listUsers = new ArrayList<>();
        listUsers.addAll(userDAO.findAllRegUsersNames());
        listUsers.addAll(userDAO.findAllManagersNames());
        return listUsers;
    }

    public ArrayList<String> getAllInvitedGroups(int idMeeting){
        return new ArrayList<>();
    }

    public ArrayList<String> getAllGroups(){
        ArrayList<String> listGroups = new ArrayList<>();
        listGroups.addAll(groupDAO.findAll());
        return listGroups;
    }
}
