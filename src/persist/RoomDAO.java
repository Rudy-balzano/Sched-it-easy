package persist;

import core.Equipment;

public interface RoomDAO {
    boolean insert (String name, int capacity, Equipment[] eq);
}
