package persist;

import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;

public class MySQLUserDAO implements UserDAO {

    private ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
    private Connection connection = instanceConnection.getConnection();

    public MySQLUserDAO(){ }

    public HashMap<String,String> findByUsername(String username){

        HashMap<String,String> infoUser = new HashMap<String, String>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where username = '" + username + "';");
            if(rs.next()){
                infoUser.put("username",rs.getString(2));
                infoUser.put("password",rs.getString(3));
                infoUser.put("firstname",rs.getString(4));
                infoUser.put("lastname",rs.getString(5));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return infoUser;
    }

}
