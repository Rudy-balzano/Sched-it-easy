package persist;

import core.Equipment;
import core.Room;
import javafx.util.Pair;

import java.util.ArrayList;

public interface RoomDAO {
    boolean insert (String name, int capacity, ArrayList<Pair<String,Integer>> equipments);

    boolean update (String name, int capacity, ArrayList<Pair<String,Integer>> equipments);

    boolean delete (String name);

    Room findBy(String name);

    ArrayList<String> findAll();
}
