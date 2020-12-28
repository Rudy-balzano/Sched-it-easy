package persist;

import core.Equipment;
import core.Room;

public interface RoomDAO {
    public boolean insert (String name, int capacity, Equipment[] eq);

    public boolean update (String name, int capacity, Equipment[] eq);

    public boolean delete (String name);

    public Room findByName(String name);
}
