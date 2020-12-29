package persist;

import core.PayableEquipment;
import core.RoomEquipment;

import java.util.ArrayList;

public interface EquipmentDAO {
    RoomEquipment findRoomEquipmentBy(String name);

    PayableEquipment findPayableEquipmentBy(String name);

    ArrayList<RoomEquipment> findAllRoomEquipment();

    ArrayList<PayableEquipment> findAllPayableEquipment();

}
