package core;
import persist.FactoryDAOImpl;
import persist.InvitationDAO;
import persist.UserDAO;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class that represent the facade for a schedule
 */
public class ScheduleFacade {
    /**
     * factoryDAO
     */
    private final FactoryDAOImpl factoryDAO;
    /**
     * userDAO
     */
    private final UserDAO userDAO;
    /**
     * invitationDAO
     */
    private final InvitationDAO invitationDAO;

    /**
     * Constructor of ScheduleFacade
     */
    public ScheduleFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.invitationDAO = factoryDAO.createInvitationDAO();
    }

    /**
     * Display every meetings associated for the username in parameters
     * @param username
     * @return every meetings associated for the username
     */
    public Collection<Meeting> checkSchedule(String username) {
        return userDAO.findSchedule(username);
    }

    /**
     * Return every invitations associated for the username in parameters
     * @param username
     * @return every invitations associated for the username
     */
    public ArrayList<Invitation> checkInvitation(String username) { return invitationDAO.findInvitation(username); }
}
