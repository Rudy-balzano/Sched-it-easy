package persist;

import core.Group;

import java.util.ArrayList;

/**
 * This interface implements methods to manipulate Group related persistent data.
 */
public interface GroupDAO {

    /**
     * Searches all the existing Groups.
     * @return a collection of all the existing Group's names.
     */
    ArrayList<String> findAll();

    /**
     * Retrieve a Group given its name.
     * @param name the Group's name.
     * @return the Group that matches the given name.
     */
    Group findByName(String name);

    /**
     * Creates a new persistent Group.
     * @param name the new persistent Group's name.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean insert(String name);

    /**
     * Deletes a persistent Group.
     * @param name the Group's name.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean delete(String name);

    /**
     * Adds a new member to a persistent Group.
     * @param username the User's username.
     * @param groupName the Group's name.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean addMember(String username, String groupName);

    /**
     * Deletes a member from a persistent Group.
     * @param username the User's username.
     * @param groupName the Group's name.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean deleteMember(String username, String groupName);


}
