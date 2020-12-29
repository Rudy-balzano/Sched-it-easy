package persist;

import core.Equipment;
import core.PayableEquipment;
import core.RoomEquipment;

import java.util.ArrayList;

public interface EquipmentDAO {

    Equipment findBy(String name);

    ArrayList<Equipment> findAll();

    String findRoomEquipmentBy(String nameRoom, String nameEquipment);

    ArrayList<String> findAllRoomEquipment(String nameRoom);


}
