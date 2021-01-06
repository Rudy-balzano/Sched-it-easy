package core;

import persist.InvitationDAO;
import persist.UserDAO;
import persist.FactoryDAOImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InvitationFacade {
    private final UserDAO userDAO;
    private final InvitationDAO invitationDAO;
    private String u;


    public InvitationFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.invitationDAO = factoryDAO.createInvitationDAO();
        this.u = SessionFacade.getConnectedUser().getUserName();
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
}
