package core;

import org.apache.commons.lang3.tuple.Pair;
import persist.EquipmentDAO;
import persist.FactoryDAOImpl;
import java.util.ArrayList;

import persist.RoomDAO;
import persist.TopicDAO;

public class RoomTopicFacade {
    private FactoryDAOImpl factoryDAO;
    private RoomDAO roomDAO;
    private TopicDAO topicDAO;
    private EquipmentDAO equipmentDAO;

    public RoomTopicFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.roomDAO = factoryDAO.createRoomDAO();
        this.topicDAO = factoryDAO.createTopicDAO();
        this.equipmentDAO = factoryDAO.createEquipmentDAO();
    }


    public ArrayList<Equipment> getEquipments() {
        return equipmentDAO.findAll();
    }

    public Equipment displayEquipmentByName(String name){
        return equipmentDAO.findBy(name);
    }

    public ArrayList<Pair<String, Integer>> getEquipmentsForRoom(String nameRoom) {
        return roomDAO.findEquipmentsForRoom(nameRoom);
    }




    public Boolean addRoom(String name, int capacity, ArrayList<Pair<String,Integer>> equipments){
        return roomDAO.insert(name,capacity,equipments);
    }

    public Boolean updateRoom(String name,int cap, ArrayList<Pair<String,Integer>> equipments){
        return roomDAO.update(name,cap,equipments);
    }
    public Boolean deleteRoom(String name){
        return roomDAO.delete(name);
    }
    public ArrayList<String> getRooms(){
        return roomDAO.findAll();
    }
    public Room displayRoomByName(String name){
        return roomDAO.findBy(name);
    }





    public Boolean addTopic(String name, String desc) {
        return topicDAO.insert(name,desc);
    }
    public Boolean updateTopic(String name, String desc) {
        return topicDAO.update(name,desc);
    }
    public Boolean deleteTopic(String name){
        return topicDAO.delete(name);
    }
    public ArrayList<Topic> getTopics(){
        return topicDAO.findAll();
    }
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