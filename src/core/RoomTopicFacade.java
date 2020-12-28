package core;
import persist.FactoryDAOImpl;
import java.util.ArrayList;
import persist.RoomDAO;

public class RoomTopicFacade {
    private FactoryDAOImpl factoryDAO;
    private RoomDAO roomDAO;

    public RoomTopicFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.roomDAO = factoryDAO.createRoomDAO();
    }

    public ArrayList<String> displayRooms(){
        //TODO
        return new ArrayList<>();
    }
    public void displayRoomsById(String name){
        //TODO
    }
    public void displayTopics(){
        //TODO
    }
    public  void displayTopicById(String name){
        //TODO
    }

    public Boolean addRoom(String name, int capacity, Equipment[] equipment){
        return roomDAO.insert(name,capacity,equipment);
    }

    public Boolean UpdateRoom(String name,int cap, Equipment[] eq){
        //TODO
        return false;
    }
    public Boolean DeleteRoom(String name){
        //TODO
        return false;
    }
    public Boolean addTopic(String name, String desc) {
        //TODO
        return false;
    }
    public Boolean UpdateTopic(String name, String desc) {
        //TODO
        return false;
    }
    public Boolean DeleteTopic(String name){
        //TODO
        return false;
    }

}
