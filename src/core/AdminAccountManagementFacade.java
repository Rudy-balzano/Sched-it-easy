package core;

import persist.AdminDAO;
import persist.FactoryDAOImpl;
import persist.UserDAO;

import java.util.ArrayList;
import java.util.Map;

public class AdminAccountManagementFacade {
    private static Admin connectedAdmin;
    private FactoryDAOImpl factoryDAO;
    private UserDAO userDAO;
    private AdminDAO adminDAO;

    public AdminAccountManagementFacade(){
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.adminDAO = factoryDAO.createAdminDAO();
    }

    public void getAllUsers(){
        //Used to display all users (managers & clients) in the "Users accounts" tab
        Map<String,Boolean> usersNames = userDAO.findAllNames();
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

    public boolean addNewAdmin(String username, String firstName,String lastName, String password){
        //Used to create a new admin account
        return adminDAO.insertAdmin(username,firstName,lastName,password);
    }

}
