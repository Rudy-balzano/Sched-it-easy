package persist;

import core.Admin;

/**
 * This interface implements the method used by an AdminDAO to manipulates persistent data.
 */
public interface AdminDAO {
    /**
     * Searches into the database the admin that matches parameters
     * @param username the admin username to search for.
     * @return the admin that matches the username.
     */
    Admin findByUsername(String username);

    /**
     * Makes a new admin persistent
     * @param username the admin's username
     * @param first the admin's firstname
     * @param last the admin's lastname
     * @param mdp the admin's password
     * @return boolean that indicates if the operation was successful
     */
    boolean insertAdmin(String username, String first, String last, String mdp);

    /**
     * Deletes an admin from the persistence
     * @param username admin to delete username
     * @return boolean that indicates if the operation was successful
     */
    boolean deleteAdmin(String username);
}
