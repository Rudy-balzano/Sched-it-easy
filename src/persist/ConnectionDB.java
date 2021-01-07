package persist;

/**
 * This interface implements the method that enables to connect to a database .
 */
public interface ConnectionDB {
    /**
     * This method connects to the database associated to the calling instance's class.
     * @return boolean that indicates if the operation was successful.
     */
    boolean connectionToDB();
}
