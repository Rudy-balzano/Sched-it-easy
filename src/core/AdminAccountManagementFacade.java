package core;

import persist.AdminDAO;
import persist.FactoryDAOImpl;
import persist.UserDAO;

import java.util.Collection;
import java.util.Map;

/**
 * Facade for the Account Management made by an Admin
 * @verson 1.0
 * @see gui.controllers.AdminRegisterController
 * @see gui.controllers.AdminUsersManagementController
 */
public class AdminAccountManagementFacade {
    /**
     * userDAO
     */
    private final UserDAO userDAO;
    /**
     * adminDAO
     */
    private final AdminDAO adminDAO;

    /**
     * Constructor of AdminAccountManagementFacade
     */
    public AdminAccountManagementFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.adminDAO = factoryDAO.createAdminDAO();
    }

    /**
     * Function used to display all regular users in the "Users accounts" tab
     * @return a Collection of all regular users username in the "Users accounts" tab
     */
    public Collection<String> getAllRegUserNames(){
        return userDAO.findAllRegUsersNames();
    }

    /**
     * Function used to display all managers in the "Users accounts" tab
     * @return a Collection of all managers username in the "Users accounts" tab
     */
    public Collection<String> getAllManagersNames(){
        return userDAO.findAllManagersNames();
    }

    /**
     * Function used to display information about a user thanks to his username in the "Users accounts" tab
     * @param name
     * @return a User and all his infos (username, password, firstname, lastname)
     */
    public User seeInfos(String name){
        return userDAO.findByUsername(name);
    }

    /**
     * Function used to make a regular user become a manager thanks to his username
     * @param name
     * @return True if the regular user has became a manager, false if not.
     */
    public boolean makeManager(String name){
        return userDAO.makeManager(name);
    }

    /**
     * Function used to delete a user's account thanks to his username
     * @param name
     * @return True if the user has been deleted, false if not.
     */
    public boolean deleteUser(String name){
        return userDAO.deleteUser(name);
    }

    /**
     * Function used to create a new admin account
     * @param username
     * @param firstName
     * @param lastName
     * @param password
     * @return True if the new admin account has been created, false if not.
     */
    public boolean registerNewAdmin(String username, String firstName,String lastName, String password){

        return adminDAO.insertAdmin(username,firstName,lastName,password);
    }

}
