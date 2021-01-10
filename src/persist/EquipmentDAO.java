package persist;

import core.Equipment;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This interface implements the method used by an EquipmentDAO to manipulates persistent Equipment related data.
 */
public interface EquipmentDAO {

    /**
     * Searches into the database the equipment that matches the parameter.
     * @param name the equipment name to search for.
     * @return the equipment that matches the given name (or null).
     */
    Equipment findBy(String name);

    /**
     * Searches into the database all the equipments saved.
     * @return A collection of equipments.
     */
    ArrayList<Equipment> findAll();

    /**
     * Save in the database equipments and the person wko rented it
     */
    void rentEquipment(Collection<String> equipments, String username, int idMeeting);

    /**
     * Searches in the database and return the rented equipment for a given user and meeting
     * @return A collection of equipments
     */
    Collection<Equipment> getRentedEquipment(String username, int idMeeting);



}
