package persist;

import core.Equipment;
import core.Room;

import java.util.ArrayList;

public interface RoomDAO {
    boolean insert (String name, int capacity, Equipment[] eq);

    boolean update (String name, int capacity, Equipment[] eq);

    boolean delete (String name);

    Room findBy(String name);

    ArrayList<String> findAll();
}
