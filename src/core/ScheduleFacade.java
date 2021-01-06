package core;

import persist.FactoryDAOImpl;
import persist.UserDAO;
import java.util.Collection;

public class ScheduleFacade {
    private final FactoryDAOImpl factoryDAO;
    private final UserDAO userDAO;
    public ScheduleFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
    }
    public Collection<Meeting> checkSchedule(String username) {
        return userDAO.findSchedule(username);
    }
}
