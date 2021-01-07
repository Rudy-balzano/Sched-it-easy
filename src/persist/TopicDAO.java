package persist;

import core.Topic;

import java.util.ArrayList;

/**
 * This interface implements methods to manipulate persistent Topic related data.
 */
public interface TopicDAO {

    /**
     * Creates a new persistent Topic.
     * @param name topic's name.
     * @param description topic's description.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean insert (String name, String description);

    /**
     * Update an already existing persistent Topic.
     * @param name the topic's new name.
     * @param description the topic's new description.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean update(String name, String description);
    //TODO Ne pas pouvoir changer le nom car c'est la clé primaire

    /**
     * Deletes a persistent Topic.
     * @param name the topic's name.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean delete(String name);

    /**
     * Retrieve the persistent Topic that matches the given name.
     * @param name the Topic's name.
     * @return the Topic that matches the given name.
     */
    Topic findBy(String name);

    /**
     * Retrieves all the persistent Topics.
     * @return a collection of all the persistent Topcics.
     */
    ArrayList<Topic> findAll();
}
