package core;

import javafx.scene.control.TextField;
import persist.ConnectionDBMySQL;
import persist.MySQLFactoryDAO;
import persist.MySQLUserDAO;

import java.util.*;

/**
 *
 */
public class SessionFacade {

    private User connectedUser;
    private MySQLFactoryDAO mySQLFactoryDAO;
    private MySQLUserDAO mySQLUserDAO;


    /**
     * Default constructor
     */
    public SessionFacade() {
        mySQLFactoryDAO = MySQLFactoryDAO.getInstance();
        mySQLUserDAO = mySQLFactoryDAO.createUserDAO();
    }

    /**
     * @param String username
     * @param String password
     * @param String firstName
     * @param String lastName
     */
//    public void register(void String username, void String password, void String firstName, void String lastName) {
//        // TODO implement here
//    }

    /**
     * @param username
     * @param password
     */
    public boolean login(String username, String password) {

        Boolean check = false;
        String u = username;
        String p = password;

/*
        mySQLFactoryDAO.getInstance();
*/
        // on récupère les infos du user : username, password, firstname, lastname
        connectedUser = mySQLUserDAO.findByUsername(u);

        // si on trouve le user dans la db
        if (connectedUser != null){

            check = verification(connectedUser.getPassword(), p);
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
    public Boolean verification(String userPassword, String passwordEnter){
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
