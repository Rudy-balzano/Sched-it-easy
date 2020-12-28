package persist;

import core.Equipment;
import core.Room;

import java.util.ArrayList;

public interface RoomDAO {
    public boolean insert (String name, int capacity, Equipment[] eq);

    public boolean update (String name, int capacity, Equipment[] eq);

    public boolean delete (String name);

    public Room findBy(String name);

    public ArrayList<Room> findAll();
}
