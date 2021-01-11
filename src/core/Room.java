package core;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;

/**
 * Class that represents a Room
 * @version 1.0
 */
public class Room {
    /**
     * Name of the room
     * @see Room#getNameRoom()
     * @see Room#setNameRoom(String)
     */
    private String nameRoom;
    /**
     * Capacity of the room; How many people can be in this room
     * @see Room#getCapacity()
     * @see Room#setCapacity(int)
     */
    private int capacity;
    /**
     * List of all the equipments for this room
     * @see Room#setEquipment(ArrayList)
     * @see Room#getEquipment()
     */
    private ArrayList<Pair<String,Integer>> equipments;

    /**
     * Constructor of Room
     * @param nameRoom
     * @param capacity
     * @param equipments
     * @see Room#nameRoom
     * @see Room#capacity
     * @see Room#equipments
     */

    public Room( String nameRoom, int capacity, ArrayList<Pair<String,Integer>> equipments) {

        this.nameRoom = nameRoom;
        this.capacity = capacity;
        this.equipments = equipments;
    }

    /**
     * Constructor where everything is initialized to null
     */
    public Room() {
    };

    /**
     * Return the name of the room
     * @return the name of the room
     */
    public String getNameRoom() {
        return nameRoom;
    }

    /**
     * Set the name of the room
     * @param nameRoom
     */
    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    /**
     * Return the capacity of the room
     * @return the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set the capacity of the room
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity =  capacity;
    }

    /**
     * Return the list of equipment for the room
     * @return the list of equipment for the room
     */
    public ArrayList<Pair<String,Integer>> getEquipment() {
        return equipments;
    }

    /**
     * Set the list of equipment for the room
     * @param equipments
     */
    public void setEquipment(ArrayList<Pair<String,Integer>> equipments) {
        this.equipments = equipments;
    }
}

