package persist;

import java.util.ArrayList;
import java.sql.*;

public class MySQLUserDAO implements UserDAO {

    ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
    Connection connection = instanceConnection.getConnection();

    public ArrayList<String> findByUsername(String username){

        ArrayList<String> user = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where username = '" + username + "';");
            if(rs.next()){
                user.add(rs.getString(2));
                user.add(rs.getString(3));
                user.add(rs.getString(4));
                user.add(rs.getString(5));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return user;
    }

}
