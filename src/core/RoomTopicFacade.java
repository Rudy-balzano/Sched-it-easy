package core;
import persist.FactoryDAOImpl;
import java.util.ArrayList;
import persist.RoomDAO;
import persist.TopicDAO;

public class RoomTopicFacade {
    private FactoryDAOImpl factoryDAO;
    private RoomDAO roomDAO;
    private TopicDAO topicDAO;

    public RoomTopicFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.roomDAO = factoryDAO.createRoomDAO();
        this.topicDAO = factoryDAO.createTopicDAO();
    }

    public ArrayList<String> displayRooms(){
        //TODO
        return new ArrayList<>();
    }
    public void displayRoomsByName(String name){
        //TODO
    }
    public ArrayList<Topic> displayTopics(){
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

    public Boolean addRoom(String name, int capacity, Equipment[] equipment){
        return roomDAO.insert(name,capacity,equipment);
    }

    public Boolean UpdateRoom(String name,int cap, Equipment[] eq){
        return roomDAO.update(name,cap,eq);
    }
    public Boolean DeleteRoom(String name){
        return roomDAO.delete(name);
    }
    public Boolean addTopic(String name, String desc) {
        return topicDAO.insert(name,desc);
    }
    public Boolean UpdateTopic(String name, String desc) {
        return topicDAO.update(name,desc);
    }
    public Boolean DeleteTopic(String name){
        return topicDAO.delete(name);
    }

}