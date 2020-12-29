package persist;

import core.Equipment;
import core.PayableEquipment;
import core.Room;
import core.RoomEquipment;

import java.util.ArrayList;

public interface EquipmentDAO {

    public RoomEquipment findRoomEquipmentBy(String name);
    public PayableEquipment findPayableEquipmentBy(String name);

    public ArrayList<RoomEquipment> findAllRoomEquipment();
    public ArrayList<PayableEquipment> findAllPayableEquipment();

}
