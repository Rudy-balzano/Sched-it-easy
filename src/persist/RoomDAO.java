package persist;

import core.Room;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface implements the method used by a RoomDAO to manipulates persistent data.
 */
public interface RoomDAO {
    /**
     * Creates a new persistent Room.
     * @param name room's name.
     * @param capacity room's capacity.
     * @param equipments room's equipment.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean insert (String name, int capacity, ArrayList<Pair<String,Integer>> equipments);

    /**
     * Update the given persistent Room (identified by it's name) with the param values.
     * @param name the Room's name.
     * @param capacity the Room's capacity.
     * @param equipments the Room's equipment.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean update (String name, int capacity, ArrayList<Pair<String,Integer>> equipments);
    //TODO Ne pas update le nom car c'est la clé principale de Room.

    /**
     * Deletes the persistent Room that matches the given name.
     * @param name the room's name.
     * @return boolean that indicates if the operation has been successful.
     */
    boolean delete (String name);

    /**
     * Retrieve the Room that matches the given name.
     * @param name the Room's name.
     * @return the matching Room.
     */
    Room findBy(String name);

    /**
     * Searches for all the Rooms contained in the persistence.
     * @return a collection composed of the returned Room's names.
     */
    ArrayList<String> findAll();

    /**
     * Find the equipment associated to the Room that matches the given Room name.
     * @param roomName the Room's name.
     * @return a collection of Equipment name associated to its quantity in the given Room.
     */
    ArrayList<Pair<String, Integer>> findEquipmentsForRoom(String roomName);

    /**
     * Searches into the database the Room that are associated to a Meeting.
     * @return a map that associates the Meeting's id and the Room's name.
     */
    HashMap<Integer,String> getAllTakenRooms();

}
