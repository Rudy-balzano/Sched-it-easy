package persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import core.User;

public class MySQLUserDAO implements UserDAO {

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLUserDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public Collection<String> findAllRegUsersNames() {
        Collection<String> res = new ArrayList<String>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select firstname,lastname,isManager from users;");
            while(rs.next()){
                //Concatenate first and last names
                String name = rs.getString(1) + " " + rs.getString(2);
                res.add(name);
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return res;
    }

    @Override
    public Collection<String> findAllManagersNames() {
        Collection<String> res = new ArrayList<String>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT firstname,lastname,isManager FROM users WHERE isManager = 1;");
            while(rs.next()){
                //Concatenate first and last names
                String name = rs.getString(1) + " " + rs.getString(2);
                res.add(name);
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return res;
    }


    @Override
    public User findByUsername(String username){

        User u = new User();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where username = '" + username + "';");
            if(rs.next()){
                u.setUserName(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setFirstName(rs.getString(3));
                u.setLastName(rs.getString(4));
                u.setIsManager(rs.getBoolean(5));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return u;
    }

    @Override
    public boolean insertWaitingUser(String username, String first, String last, String mdp){
        boolean result = false;

        if(!verify(username)){

            try{
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("insert into waiting_users (username,password,first_name,last_name) values('" + username + "','" + mdp + "','" + first + "','" + last +"');");
                result = true;
            } catch (SQLException ex){
                System.out.println(ex);
            }
        }

        return result;
    }

    @Override
    public boolean makeManager(String username) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE users SET isManager = 1 WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }

    @Override
    public boolean deleteUser(String username) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeQuery("DELETE FROM users WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }

    private boolean verify(String username){
        boolean exist = false;

        try {
            Statement stmt1 = connection.createStatement();
            Statement stmt2 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from users where username = '" + username + "';");
            ResultSet rs2 = stmt2.executeQuery("select * from waiting_users where username = '" + username + "';");
            if (rs1.next() || rs2.next()) {
                exist = true;
            }
        } catch (SQLException ex){

            System.out.println(ex);

        }

        return exist;
    }
}


