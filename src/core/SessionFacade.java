package core;

import javafx.scene.control.TextField;
import persist.MySQLUserDAO;

import java.util.*;

/**
 *
 */
public class SessionFacade {

    User connectedUser;
//    MySQLFactoryDAO mySQLFactoryDAO;
    MySQLUserDAO mySQLUserDAO;


    /**
     * Default constructor
     */
    public SessionFacade() {
    }

    /**
     *
     */
//    private User connectedUser;

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

        ArrayList<String> InfoUser = new ArrayList<>();
/*
        mySQLFactoryDAO.getInstance();
*/
        InfoUser = mySQLUserDAO.findByUsername(u);

        connectedUser = new User(InfoUser.get(0), InfoUser.get(1), InfoUser.get(2), InfoUser.get(3));

        if (connectedUser != null){
            check = verification(connectedUser, p);
            System.out.println("is checking...");
        }
        else {
            check = false;
        }

        if (check) {
            System.out.println("You are logged !");
        }
        else {
            System.out.println("Sorry, bad username or password, try again !");
        }

    }

    public Boolean verification(User user, String passwordEnter){
        Boolean check = false;

        String password = user.getPassword();
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
