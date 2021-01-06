package core;

import persist.FactoryDAOImpl;
import persist.InvitationDAO;
import persist.UserDAO;

import java.util.ArrayList;
import java.util.Collection;

public class ScheduleFacade {
    private final FactoryDAOImpl factoryDAO;
    private final UserDAO userDAO;
    private final InvitationDAO invitationDAO;
    public ScheduleFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.invitationDAO = factoryDAO.createInvitationDAO();
    }
    public Collection<Meeting> checkSchedule(String username) {
        return userDAO.findSchedule(username);
    }
    public ArrayList<Invitation> checkInvitation(String username) { return invitationDAO.findInvitation(username); }
}
