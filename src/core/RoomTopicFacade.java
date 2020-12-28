package core;
import persist.FactoryDAOImpl;
import java.util.ArrayList;

public class RoomTopicFacade {
    private FactoryDAOImpl factoryDAO;
    public RoomTopicFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
    }

    public ArrayList<String> displayRooms(){

    };
    public displayRoomsById(Int){};
    public displayTopics(){};
    public  displayTopicById(String){};

    public Boolean addRoom(String name, int capacity, Equipment[] equipment){
        Boolean check = false;
    //ATTENTION FAIRE UN IF AVEC CE QUE RETOURNE INSERT
        roomDAO.insert(String name, int capacity, Equipment[] equipment);
    };
    public Boolean UpdateRoom(Room,Int, String, Int, Equipment[]){};
    public Boolean DeleteRoom(Room,Int){};
    public Boolean addTopic(Topic,String, String) {};
    public Boolean UpdateTopic(Topic,String, String) {};
    public Boolean DeleteTopic(Topic){}

}
