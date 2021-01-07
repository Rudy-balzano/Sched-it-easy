package persist;

import core.Equipment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class implements EquipmentDAO and implements methods to manipulate Equipment related persistent data from a MySQL database.
 */
public class MySQLEquipmentDAO implements EquipmentDAO{

    /**
     * Connection to the database.
     */
    private final Connection connection;

    /**
     * Constructs the MySQLEquipmentDAO
     */
    public MySQLEquipmentDAO(){
        ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
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
            System.out.println(ex.getSQLState());
        }
        return equipments;
    }





}
