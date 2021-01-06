package persist;
import java.sql.Connection;
import core.Room;
import core.Equipment;
import core.Topic;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.*;
import java.util.ArrayList;

public class MySQLRoomDAO implements RoomDAO {

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLRoomDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    private boolean insertRoom(String nameRoom, int capacity){
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

    private boolean insertEquipmentForRoom(String nameRoom, String nameEquipment, int quantity){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into roomEquipments (nameRoom, nameEquipment, quantity) values('" + nameRoom + "','" + nameEquipment + "', '" +quantity + "');");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public boolean insert(String nameRoom, int capacity, ArrayList<Pair<String,Integer>> equipments) {
        boolean result1 = false;
        boolean result2 = false;
        result1 = insertRoom(nameRoom, capacity);
        for (int i=0; i<equipments.size(); i++){
            result2 = insertEquipmentForRoom(nameRoom, equipments.get(i).getKey(), equipments.get(i).getValue());
        }
        if (result1 & result2){
            return true;
        }else {
            return false;
        }
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
            System.out.println(ex);
        }
        if (result1 & result2){
            return true;
        }else {
            return false;
        }
    }

    private boolean updateEquipmentsForRoom (String name, ArrayList<Pair<String,Integer>> equipments){
        boolean result1 = false;
        boolean result2 = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM roomEquipments WHERE nameRoom = '" + name + "';");
            result1 = true;
            for (int i=0; i<equipments.size(); i++){
                result2 = insertEquipmentForRoom(name, equipments.get(i).getKey(), equipments.get(i).getValue());
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
        boolean result1 = false;
        boolean result2 = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM rooms WHERE nameRoom = '" + name + "';");
            result1 = true;
            result2 = deleteEquipmentForRoom(name);
        } catch (SQLException ex){
            System.out.println(ex);
        }
        if (result1 & result2){
            return true;
        }else {
            return false;
        }
    }

    private boolean deleteEquipmentForRoom (String name){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM roomEquipments WHERE nameRoom = '" + name + "';");
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
            System.out.println(ex);
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
    public String findRoomEquipmentBy(String nameRoom, String nameEquipment) {

        String equipment = null;

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomEquipments where nameRoom = '" + nameRoom + "' and nameEquipment = '"+ nameEquipment +"';");
            if(rs.next()){
                equipment = rs.getString(2);
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return equipment;
    }


}

