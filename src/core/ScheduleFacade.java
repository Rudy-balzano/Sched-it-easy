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
    //Sûrement mettre en type de retour une liste de meeting
    public void checkSchedule(String username) {
        userDAO.findSchedule(username);
    }
}
