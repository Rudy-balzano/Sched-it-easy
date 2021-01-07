package core;

import org.apache.commons.lang3.tuple.Pair;
import persist.EquipmentDAO;
import persist.FactoryDAOImpl;
import java.util.ArrayList;

import persist.RoomDAO;
import persist.TopicDAO;

/**
 * Class that represents the facade for rooms and topics
 */
public class RoomTopicFacade {
    /**
     * factoryDAO
     */
    private FactoryDAOImpl factoryDAO;
    /**
     * roomDAO
     */
    private RoomDAO roomDAO;
    /**
     * topicDAO
     */
    private TopicDAO topicDAO;
    /**
     * equipmentDAO
     */
    private EquipmentDAO equipmentDAO;

    /**
     * Default constructor of RoomTopicFacade
     */
    public RoomTopicFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.roomDAO = factoryDAO.createRoomDAO();
        this.topicDAO = factoryDAO.createTopicDAO();
        this.equipmentDAO = factoryDAO.createEquipmentDAO();
    }

    /**
     * Function used to see every Equipments
     * @return an arrayList of everyEquipments
     */
    public ArrayList<Equipment> getEquipments() {
        return equipmentDAO.findAll();
    }

    /**
     * Function used to see an equipment's info thanks to his name
     * @param name
     * @return the equipment thanks to his name
     */
    public Equipment displayEquipmentByName(String name){
        return equipmentDAO.findBy(name);
    }

    /**
     * Function used to see every equipment available for the room with the name in parameter
     * @param nameRoom
     * @return every equipments available for this room
     */
    public ArrayList<Pair<String, Integer>> getEquipmentsForRoom(String nameRoom) {
        return roomDAO.findEquipmentsForRoom(nameRoom);
    }


    /**
     * Function used to add a room
     * @param name
     * @param capacity
     * @param equipments
     * @return True if the room has been created, false if not
     */
    public Boolean addRoom(String name, int capacity, ArrayList<Pair<String,Integer>> equipments){
        return roomDAO.insert(name,capacity,equipments);
    }

    /**
     * Function used to add a room
     * @param name
     * @param cap
     * @param equipments
     * @return True if the room has been updated, false if not
     */
    public Boolean updateRoom(String name,int cap, ArrayList<Pair<String,Integer>> equipments){
        return roomDAO.update(name,cap,equipments);
    }

    /**
     * Function used to add a room
     * @param name
     * @return True if the room has been deleted, false if not
     */
    public Boolean deleteRoom(String name){
        return roomDAO.delete(name);
    }

    /**
     * Function used to see every rooms existing
     * @return an arrayList of every rooms existing
     */
    public ArrayList<String> getRooms(){
        return roomDAO.findAll();
    }

    /**
     * Function used to see the room's info for the room name in parameter
     * @param name
     * @return the room associated to this name
     */
    public Room displayRoomByName(String name){
        return roomDAO.findBy(name);
    }

    /**
     * Function used to add a topic
     * @param name
     * @param desc
     * @return True if the topic has been created, False if not
     */
    public Boolean addTopic(String name, String desc) {
        return topicDAO.insert(name,desc);
    }

    /**
     * Function used to update a topic
     * @param name
     * @param desc
     * @return True if the topic has been updated, False if not
     */
    public Boolean updateTopic(String name, String desc) {
        return topicDAO.update(name,desc);
    }

    /**
     * Function used to delete a topic
     * @param name
     * @return True if the topic has been deleted, False if not
     */
    public Boolean deleteTopic(String name){
        return topicDAO.delete(name);
    }

    /**
     * Function used to see every topics existing
     * @return a list of every topics existing
     */
    public ArrayList<Topic> getTopics(){
        return topicDAO.findAll();
    }

    /**
     * Function used to see the Topic informations associated to the topic name in parameters
     * @param name
     * @return the topic associated to this name
     */

    public Topic getTopicByName(String name){ return topicDAO.findBy(name);}

    /**
     * Function used to display every topics by name
     * @param name
     */
    public  void displayTopicByName(String name){
        Topic topic = topicDAO.findBy(name);
        if(topic == null){
            System.out.println("No topic matching specified name found");
            //TODO Traiter erreur ?
        }else{
            //TODO envoyer à la vue
        }
    }

}