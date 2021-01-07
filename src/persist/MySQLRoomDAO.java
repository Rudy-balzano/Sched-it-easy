package persist;

import core.Room;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implements RoomDAO and implements methods to manipulate Room related persistent data from a MySQL databse.
 */
public class MySQLRoomDAO implements RoomDAO {

    /**
     * Connection to the database.
     */
    private final Connection connection;

    /**
     * Constructs the MySQLRoomDAO.
     */
    public MySQLRoomDAO(){
        ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    /**
     * Inserts a room into the database. Used in the insert method.
     * @see this.insert
     * @param nameRoom the room's name.
     * @param capacity the room's capacity.
     * @return boolean that indicates if the operation has been successful.
     */
    private boolean insertRoom(String nameRoom, int capacity){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into rooms (nameRoom,capacity) values('" + nameRoom + "','" + capacity + "');");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return result;
    }

    /**
     * Inserts an Equipment into the database. Used in the insert method.
     * @param nameRoom the related room's name.
     * @param nameEquipment the equipment's name.
     * @param quantity the quantity of the given equipment in the given room.
     * @return boolean that indicates if the operation has been successful.
     */
    private boolean insertEquipmentForRoom(String nameRoom, String nameEquipment, int quantity){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into roomEquipments (nameRoom, nameEquipment, quantity) values('" + nameRoom + "','" + nameEquipment + "', '" +quantity + "');");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return result;
    }

    @Override
    public boolean insert(String nameRoom, int capacity, ArrayList<Pair<String,Integer>> equipments) {
        boolean result1;
        boolean result2 = false;
        result1 = insertRoom(nameRoom, capacity);
        for (Pair<String, Integer> equipment : equipments) {
            result2 = insertEquipmentForRoom(nameRoom, equipment.getKey(), equipment.getValue());
        }
        return result1 & result2;
    }

    @Override
    public boolean update(String name, int capacity, ArrayList<Pair<String,Integer>> equipments) {
        boolean result1 = false;
        boolean result2 = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE rooms SET capacity = '" + capacity + "' WHERE nameRoom = '" + name + "';");
            result1 = true;
            result2 = updateEquipmentsForRoom(name, equipments);
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return result1 & result2;
    }

    /**
     * Update the equipment associated to a given room.
     * @param name the room's name.
     * @param equipments the new equipment list.
     * @return boolean that indicates if the operation has been successful.
     */
    private boolean updateEquipmentsForRoom (String name, ArrayList<Pair<String,Integer>> equipments){
        boolean result1 = false;
        boolean result2 = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM roomEquipments WHERE nameRoom = '" + name + "';");
            result1 = true;
            for (Pair<String, Integer> equipment : equipments) {
                result2 = insertEquipmentForRoom(name, equipment.getKey(), equipment.getValue());
            }
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return result1 & result2;
    }

    @Override
    public boolean delete(String name) {
        boolean result1 = false;
        boolean result2 = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM rooms WHERE nameRoom = '" + name + "';");
            result1 = true;
            result2 = deleteEquipmentForRoom(name);
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return result1 & result2;
    }

    /**
     * Delete all the equipment associated to a given room.
     * @param name the room's name.
     * @return boolean that indicates if the operation has been successful.
     */
    private boolean deleteEquipmentForRoom (String name){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM roomEquipments WHERE nameRoom = '" + name + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return result;
    }

    @Override
    public Room findBy(String name) {

        Room room = new Room();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rooms where nameRoom = '" + name + "';");
            if(rs.next()){
                room.setNameRoom(rs.getString(1));
                room.setCapacity(rs.getInt(2));
                room.setEquipment(findEquipmentsForRoom(rs.getString(1)));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }


        return room;
    }

    @Override
    public ArrayList<String> findAll() {
        ArrayList<String> rooms = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rooms ;");
            while(rs.next()){
                String room = rs.getString(1);
                rooms.add(room);
            }
        } catch (SQLException ex){
            System.out.println(ex.getSQLState());
        }
        return rooms;
    }

    @Override
    public ArrayList<Pair<String, Integer>> findEquipmentsForRoom(String roomName){
        ArrayList<Pair<String, Integer>> listEquipments = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomEquipments where nameRoom = '" + roomName + "';");

            while(rs.next()){
                listEquipments.add(new MutablePair<>(rs.getString(2), rs.getInt(3)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listEquipments;

    }


    @Override
    public HashMap<Integer,String> getAllTakenRooms() {

        HashMap<Integer,String> res = new HashMap<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomMeetings;");
            while(rs.next()){
                res.put(rs.getInt(2),rs.getString(1));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return res;
    }


}

