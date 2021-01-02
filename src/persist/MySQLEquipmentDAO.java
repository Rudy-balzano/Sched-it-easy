package persist;

import core.Equipment;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQLEquipmentDAO implements EquipmentDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLEquipmentDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }


    @Override
    public Equipment findBy(String name) {

        Equipment equipment = new Equipment();

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





}
