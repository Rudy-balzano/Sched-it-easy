package core;

import persist.FactoryDAOImpl;
import persist.UserDAO;

import java.util.Collection;

public class ManagerFacade {

    private FactoryDAOImpl factoryDAO;
    private UserDAO userDAO;

    public ManagerFacade(){
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
    }

    public boolean validateAccount(String username){

        return userDAO.validateAccount(username);

    }

    public Collection<String> getAllWaitingUsers(){
        return userDAO.findAllWaitingUsers();
    }
}
