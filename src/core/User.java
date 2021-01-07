package core;
import java.util.*;

/**
 * Class that represents an User of our app
 * @author emilie jean
 * @version 1.0
 */
public class User {
    /**
     * username of the user
     * @see User#getUserName()
     * @see User#setUserName(String)
     */
    private String username;
    /**
     * password of the user
     * @see User#getPassword()
     * @see User#setPassword(String)
     */
    private String password;
    /**
     * first name of the user
     * @see User#getFirstName()
     * @see User#setFirstName(String)
     */
    private String firstName;
    /**
     * last name of the user
     * @see User#getLastName()
     * @see User#setLastName(String)
     */
    private String lastName;
    /**
     * Boolean that represents if the user is a manager or a simple user. True = manager
     * @see User#getIsManager()
     * @see User#setIsManager(Boolean)
     */
    private Boolean isManager;
    /**
     * List of all the Meeting an user can have.
     * @see User#getListMeetings()
     * @see User#setListMeetings(ArrayList)
     */
    private ArrayList<Meeting> listMeetings;

    //private String verified;

    //private Schedule schedule;

    /**
     * Constructor of User
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param isManager
     * @param listMeetings
     *
     * @see User#username
     * @see User#password
     * @see User#firstName
     * @see User#lastName
     * @see User#isManager
     * @see User#listMeetings
     */
    public User(String username, String password, String firstName, String lastName, Boolean isManager, ArrayList<Meeting> listMeetings) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isManager = isManager;
        this.listMeetings = listMeetings;

    }

    /**
     * Constructor of User where everything is initialize at null
     */
    public User() {
        this.username = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.isManager = null;
        this.listMeetings = null;

    }
    //private void seeAccount() {

    //}


    //private void modifyAccount(void String newUsername, void String newPassword, void String newFirstName, void String newLastName) {

    // }


    // private void logout() {
    //}

    /**
     * Return the username of the User
     * @return the username of the user
     */
    public String getUserName() {
        return username;
    }

    /**
     * Set the username of the user
     * @param username
     */
    public void setUserName(String username) {
        this.username = username;
    }

    /**
     * return the password of the user
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user
     * @param password
     */
    public void setPassword( String password) {
        this.password = password;
    }

    /**
     * return the first name of the user
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the user
     * @param firstName
     */
    public void setFirstName( String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return the last name of the user
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the user
     * @param lastName
     */
    public void setLastName( String lastName) {
        this.lastName = lastName;
    }

    /**
     * Return if the user is a manager or not.
     * @return if the user is a manager or not.
     */
    public Boolean getIsManager() {
        return this.isManager;
    }

    /**
     * Set if the user is a manager or not.
     * @param isManager
     */
    public void setIsManager(Boolean isManager ) {
        this.isManager = isManager;
    }

    /**
     * return the list of the user's meetings.
     * @return the list of the user's meetings.
     */
    public ArrayList<Meeting> getListMeetings() {
        return listMeetings;
    }

    /**
     * Set the list of the user's meetings.
     * @param listMeetings
     */
    public void setListMeetings(ArrayList<Meeting> listMeetings) {
        this.listMeetings = listMeetings;
    }


    // public Boolean getVerified() {

    // return null;
    //}


    //  public void setVerified(void Boolean verified) {

    // }


    //public Schedule getSchedule() {

    //     return null;
    //  }

}
