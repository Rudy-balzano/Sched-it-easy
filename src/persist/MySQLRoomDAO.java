package persist;
import java.sql.Connection;
import core.Room;
import core.Equipment;
import java.sql.*;

public class MySQLRoomDAO implements RoomDAO {
    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLRoomDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }
    public boolean insertRoom(String nameRoom, int capacity){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into rooms (nameRoom,capacity) values('" + nameRoom + "','" + capacity + "');");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return result;
    }

    public boolean insertEquipmentRoom(String nameRoom, String nameEquipment, String descriptionEquipment){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into roomEquipments (nameRoom, nameEquipment, descriptionEquipment) values('" + nameRoom + "','" + nameEquipment + "', '" +descriptionEquipment + "');");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public boolean insert(String nameRoom, int capacity, Equipment[] eq) {
        boolean result = false;
        insertRoom(nameRoom, capacity);
        for (int i=0; i<eq.length; i++){
            insertEquipmentRoom(nameRoom, eq[i].getName(), eq[i].getDescription());
            result = true;
        }
        return result;
        }
}

