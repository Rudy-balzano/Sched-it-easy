package core;
import java.util.*;
public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isManager;
    private ArrayList<Meeting> listMeetings;

    //private String verified;

    //private Schedule schedule;


    public User(String username, String password, String firstName, String lastName, Boolean isManager, ArrayList<Meeting> listMeetings) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isManager = isManager;
        this.listMeetings = listMeetings;

    }

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

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName) {
        this.lastName = lastName;
    }

    public Boolean getIsManager() {
        return this.isManager;
    }

    public void setIsManager(Boolean isManager ) {
        this.isManager = isManager;
    }

    public ArrayList<Meeting> getListMeetings() {
        return listMeetings;
    }

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
