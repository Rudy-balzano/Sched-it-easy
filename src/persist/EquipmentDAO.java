package persist;

import core.Equipment;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public interface EquipmentDAO {

    Equipment findBy(String name);

    ArrayList<Equipment> findAll();



}
