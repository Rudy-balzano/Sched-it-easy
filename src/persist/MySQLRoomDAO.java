package persist;
import java.sql.Connection;
import core.Room;
import core.Equipment;
import core.Topic;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

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
        boolean result1 = false;
        boolean result2 = false;
        result1 = insertRoom(nameRoom, capacity);
        for (int i=0; i<eq.length; i++){
            result2 = insertEquipmentRoom(nameRoom, eq[i].getName(), eq[i].getDescription());
        }
        if (result1 & result2){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(String name, int capacity, Equipment[] eq) {
        boolean result1 = false;
        boolean result2 = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE rooms SET capacity = '" + capacity + "' WHERE nameRoom = '" + name + "';");
            result1 = true;
            result2 = updateEquipmentsForRoom(name, eq);
        } catch (SQLException ex){
            System.out.println(ex);
        }
        if (result1 & result2){
            return true;
        }else {
            return false;
        }
    }

    private boolean updateEquipmentsForRoom (String name, Equipment[] eq){
        boolean result1 = false;
        boolean result2 = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM roomEquipments WHERE nameRoom = '" + name + "';");
            result1 = true;
            for (int i=0; i<eq.length; i++){
                result2 = insertEquipmentRoom(name, eq[i].getName(), eq[i].getDescription());
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        if (result1 & result2){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(String name) {
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM rooms WHERE nameRoom = '" + name + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
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

    private ArrayList<Pair<String, Integer>> findEquipmentsForRoom(String roomName){
        ArrayList<Pair<String, Integer>> listEquipments = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomEquipments where nameRoom = '" + roomName + "';");

            while(rs.next()){
                listEquipments.add(new Pair<>(rs.getString(2), rs.getInt(3)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listEquipments;

    }

    @Override
    public ArrayList<Room> findAll() {
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooms");
            while(rs.next()){
                Room room = findBy(rs.getString(1));
                rooms.add(room);
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return rooms;
    }


}

