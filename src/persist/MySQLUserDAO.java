package persist;

import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import core.User;

public class MySQLUserDAO implements UserDAO {

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLUserDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    public User findByUsername(String username){

        User u = new User();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where username = '" + username + "';");
            if(rs.next()){
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFirstName(rs.getString(4));
                u.setLastName(rs.getString(5));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return u;
    }

    public boolean insert(String username, String first, String last, String mdp){
        boolean result = false;

        if(!verify(username)){

            try{
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("INSERT INTO waiting_users (username,password,first_name,last_name) VALUES ('" + username + "','" + mdp + "','" + first + "','" + last +"');");
                result = true;
            } catch (SQLException ex){
                System.out.println("SQL request error");
            }
        }

        return result;
    }

    private boolean verify(String username){
        boolean exist = false;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users, waiting_users where username = '" + username + "';");
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException ex){
                System.out.println("SQL request error");
        }

        return exist;
    }
}


