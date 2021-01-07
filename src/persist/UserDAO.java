package persist;

import core.Meeting;
import core.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This interface implements methods to manipulate User related persistent data.
 */
public interface UserDAO {

    /**
     * Finds all the usernames of Users that are not Managers.
     * @return a collection of usernames.
     */
    Collection<String> findAllRegUsersNames();

    /**
     * Finds all the usernames of Users that are Managers.
     * @return a collection of usernames.
     */
    Collection<String> findAllManagersNames();

    /**
     * Finds all the usernames of WaitingUsers.
     * @return a collection of usernames.
     */
    Collection<String> findAllWaitingUsers();

    /**
     * Retrieve the User that matches the given username.
     * @param username the User's username.
     * @return the matching User.
     */
    User findByUsername(String username);

    /**
     * Creates a new persistent WaitingUser.
     * @param username new WaitingUser username.
     * @param first new WaitingUser firstname.
     * @param last new WaitingUser lastname.
     * @param mdp new WaitingUser password.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean insertWaitingUser(String username, String first, String last, String mdp);

    /**
     * Makes the persistent User that matches the given username a Manager.
     * @param username username.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean makeManager(String username);

    /**
     * Deletes the persistent User that matches the given username.
     * @param username username.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean deleteUser(String username);

    /**
     * Validates a WaitingUser's account and makes him a regular User.
     * @param username WaitingUser's username.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean validateAccount(String username);

    /**
     * Deletes a WaitingUser's account.
     * @param username WaitingUser's username.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean declineWaitingUser(String username);

    /**
     * Retrieve the Meetings associated to the User that matches the given username.
     * @param username User's username.
     * @return a collection of Meetings in which the given User is involved.
     */
    Collection<Meeting> findSchedule(String username);

    /**
     * Modifies the persistent User that matches the given username.
     * @param username User's username.
     * @param firstName User's firstname.
     * @param lastName User's lastname.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean modifyUser(String username,String firstName,String lastName);

    /**
     * Declines an invitation to a Meeting.
     * @param username declining user's username.
     * @param id invitation's id.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean declineWaitingInvitation(String username, int id);

    /**
     * Confirms an invitation to a Meeting.
     * @param username accepting user's username.
     * @param id invitation's id.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean acceptWaitingInvitation(String username, int id);

    /**
     * Retrieves all the Users that belong to the Group that matches the given group name.
     * @param groupeName the Group's name.
     * @return a collection of the group's Users' usernames .
     */
    ArrayList<String> findAllByGroup (String groupeName);


}