package persist;

import core.Equipment;
import core.Room;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface RoomDAO {
    boolean insert (String name, int capacity, ArrayList<Pair<String,Integer>> equipments);

    boolean update (String name, int capacity, ArrayList<Pair<String,Integer>> equipments);

    boolean delete (String name);

    Room findBy(String name);

    ArrayList<String> findAll();

    ArrayList<Pair<String, Integer>> findEquipmentsForRoom(String roomName);

    String findRoomEquipmentBy(String nameRoom, String nameEquipment);

    HashMap<Integer,String> getAllTakenRooms();

}
