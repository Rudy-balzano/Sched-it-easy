package core;

import persist.FactoryDAOImpl;
import persist.UserDAO;

public class ScheduleFacade {
    private final FactoryDAOImpl factoryDAO;
    private final UserDAO userDAO;
    public ScheduleFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
    }
    public void checkSchedule(String username) {
        userDAO.displaySchedule(username);
    }
}
