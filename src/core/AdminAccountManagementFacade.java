package core;

import persist.AdminDAO;
import persist.FactoryDAO;
import persist.FactoryDAOImpl;
import persist.UserDAO;

import java.util.Collection;

public class AdminAccountManagementFacade {
    private static Admin connectedAdmin; //Maybe useless
    private UserDAO userDAO;
    private AdminDAO adminDAO;

    public AdminAccountManagementFacade(){
        FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.adminDAO = factoryDAO.createAdminDAO();
    }

    public Collection<String> getAllRegUserNames(){
        //Used to display all regular users in the "Users accounts" tab
        return userDAO.findAllRegUsersNames();
    }

    public Collection<String> getAllManagersNames(){
        //Used to display all managers in the "Users accounts" tab
        return userDAO.findAllManagersNames();
    }

    public User seeInfos(String name){
        //Used to display information about a user in the "Users accounts" tab
        return userDAO.findByUsername(name);
    }

    public boolean makeManager(String name){
        //Used to make a regular user become a manager
        return userDAO.makeManager(name);
    }

    public boolean deleteUser(String name){
        //Used to delete a user's account
        return userDAO.deleteUser(name);
    }

    public boolean registerNewAdmin(String username, String firstName,String lastName, String password){
        //Used to create a new admin account
        return adminDAO.insertAdmin(username,firstName,lastName,password);
    }

}
