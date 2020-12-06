package core;

import javafx.scene.control.TextField;
import persist.ConnectionDBMySQL;
import persist.FactoryDAO;
import persist.FactoryDAOImpl;
import persist.UserDAO;

import java.util.*;

/**
 *
 */
public class SessionFacade {

    private User connectedUser;
    private FactoryDAOImpl factoryDAO;
    private UserDAO userDAO;


    /**
     * Default constructor
     */
    public SessionFacade() {

    }

    /**
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
    public boolean register(String username, String firstName,String lastName, String password) {

        return userDAO.insert(username,firstName,lastName,password);

    }

    /**
     * @param username
     * @param password
     */
    public boolean login(String username, String password) {

        Boolean check = false;

        // on récupère les infos du user : username, password, firstname, lastname
        connectedUser = userDAO.findByUsername(username);

        // si on trouve le user dans la db
        if (connectedUser.getUserName() != null){

            check = verification(connectedUser.getPassword(), password);
            System.out.println("is checking...");

            // si le mot de passe entré est le bon
            if (check){
                System.out.println("You are logged !");
            }

        }
        else {
            System.out.println("Sorry, bad username or password, try again !");
        }

        return check;

    }

    /**
     * fonction qui vérifie le mot de passe de l'utilisateur
     * @param userPassword
     * @param passwordEnter
     * @return
     */
    private Boolean verification(String userPassword, String passwordEnter){
        Boolean check = false;

        if(userPassword.equals(passwordEnter)){
            check = true;
        }
        return check;
    }

    /**
     *
     */
    public String getFirstName() {
        return connectedUser.getFirstName();
    }

    /**
     *
     */
    public String getLastName() {
        return connectedUser.getLastName();
    }

    public User getConnectedUser() { return this.connectedUser;}

}
