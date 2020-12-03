package core;
import java.util.*;
public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    //private String verified;

    //private Schedule schedule;

    // private TypeRole role;

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

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

    public void setUserName( String username) {
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


    // public Boolean getVerified() {

    // return null;
    //}


    //  public void setVerified(void Boolean verified) {

    // }


    //public Schedule getSchedule() {

    //     return null;
    //  }


   /* public TypeRole getRole() {
        return null;
    }

 /*  public void setRole(void role TypeRole) {
    }

}
*/
}