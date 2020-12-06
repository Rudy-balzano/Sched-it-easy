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


    /**
     * Default constructor
     */
    public SessionFacade() {
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

        // on crée la connection à la base de données
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        UserDAO userDAO = factoryDAO.createUserDAO();

        // on récupère les infos du user : username, password, firstname, lastname
        connectedUser = userDAO.findByUsername(u);

        // si on trouve le user dans la db
        if (connectedUser.getUserName() != null){

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
