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
    public void login(String username, String password) {

        Boolean check = false;
        String u = username;
        String p = password;

        HashMap<String,String> InfoUser = new HashMap<>();
/*
        mySQLFactoryDAO.getInstance();
*/
        // on récupère les infos du user : username, password, firstname, lastname
        InfoUser = mySQLUserDAO.findByUsername(u);

        // si on trouve le user dans la db
        if (InfoUser.size() != 0){

            check = verification(InfoUser, p);
            System.out.println("is checking...");

            // si le mot de passe entré est le bon
            if (check){
                connectedUser = new User(InfoUser.get(0), InfoUser.get(1), InfoUser.get(2), InfoUser.get(3));
                System.out.println("You are logged !");
            }

        }
        else {
            System.out.println("Sorry, bad username or password, try again !");
        }

    }

    /**
     * fonction qui vérifie le mot de passe de l'utilisateur
     * @param infoUser
     * @param passwordEnter
     * @return
     */
    public Boolean verification(HashMap<String,String> infoUser, String passwordEnter){
        Boolean check = false;

        String password = infoUser.get("password");
        if(password.equals(passwordEnter)){
            check = true;
        }
        return check;
    }

    /**
     *
     */
    public void getFirstName() {
        // TODO implement here
    }

    /**
     *
     */
    public void getLastName() {
        // TODO implement here
    }

}
