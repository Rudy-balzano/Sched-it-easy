package persist;

import core.Equipment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLEquipmentDAO implements EquipmentDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLEquipmentDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }


    @Override
    public Equipment findBy(String name) {

        Equipment equipment = null;

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from equipments where equipmentName = '" + name + "';");
            if(rs.next()){
                equipment.setName(rs.getString(1));
                equipment.setDescription(rs.getString(2));
                equipment.setPrice(rs.getInt(3));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return equipment;
    }

    @Override
    public ArrayList<Equipment> findAll() {
        ArrayList<Equipment> equipments = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM equipments");
            while(rs.next()){
                Equipment equipment = new Equipment(rs.getString(1),rs.getString(2),rs.getInt(3));
                equipments.add(equipment);
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return equipments;
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


    @Override
    public ArrayList<String> findAllRoomEquipment(String nameRoom) {
        ArrayList<String> equipments = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomEquipments where nameRoom = '" + nameRoom + "';");
            while(rs.next()){
                equipments.add(rs.getString(2));
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return equipments;
    }

}
