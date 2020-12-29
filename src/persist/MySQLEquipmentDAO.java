package persist;

import core.PayableEquipment;
import core.RoomEquipment;

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
    public RoomEquipment findRoomEquipmentBy(String name) {

        RoomEquipment equipment = new RoomEquipment();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from roomEquipments where equipmentName = '" + name + "';");
            if(rs.next()){
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return equipment;
    }

    @Override
    public PayableEquipment findPayableEquipmentBy(String name) {

        PayableEquipment equipment = new PayableEquipment();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from payableEquipments where equipmentName = '" + name + "';");
            if(rs.next()){
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return equipment;
    }

    @Override
    public ArrayList<RoomEquipment> findAllRoomEquipment() {
        ArrayList<RoomEquipment> equipments = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM roomEquipments");
            while(rs.next()){
                RoomEquipment equipment = new RoomEquipment(rs.getString(1),rs.getString(2), rs.getInt(3));
                equipments.add(equipment);
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return equipments;
    }

    @Override
    public ArrayList<PayableEquipment> findAllPayableEquipment() {
        ArrayList<PayableEquipment> equipments = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM payableEquipments");
            while(rs.next()){
                PayableEquipment equipment = new PayableEquipment(rs.getString(1),rs.getString(2), rs.getInt(3));
                equipments.add(equipment);
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return equipments;
    }
}
