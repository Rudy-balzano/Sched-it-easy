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
            ResultSet rs = stmt.executeQuery("SELECT firstname,lastname,username FROM users WHERE isManager = 0;");
            while(rs.next()){
                //Concatenate first and last names
                String name = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
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
            ResultSet rs = stmt.executeQuery("SELECT firstname,lastname,username FROM users WHERE isManager = 1;");
            while(rs.next()){
                //Concatenate first and last names
                String name = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
                res.add(name);
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return res;
    }

    @Override
    public Collection<String> findAllWaitingUsers() {

        Collection<String> res = new ArrayList<String>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username,first_name,last_name FROM waiting_users;");
            while(rs.next()){
                String name = rs.getString(1) + " : " + rs.getString(2) + " " + rs.getString(3);
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
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }
    public boolean declineWaitingUser(String username) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM waiting_users WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }

    @Override
    public boolean validateAccount(String username) {
        boolean result1 = false;
        boolean result2 = false;

        User u = new User();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from waiting_users where username = '" + username + "';");
            if(rs.next()) {
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFirstName(rs.getString(4));
                u.setLastName(rs.getString(5));
                u.setIsManager(false);
            }

                try{
                    Statement stmt1 = connection.createStatement();
                    stmt1.executeUpdate("insert into users (username,password,firstname,lastname,isManager) values('" + u.getUserName() + "','" + u.getPassword() + "','" + u.getFirstName() + "','" + u.getLastName() + "','" + 0 +"');");
                    result1 = true;

                    try{
                        Statement stmt2 = connection.createStatement();
                        stmt2.executeUpdate("DELETE FROM waiting_users WHERE username = '" + username + "';");
                        result2 = true;
                    } catch (SQLException ex){
                        System.out.println(ex);
                    }
                } catch (SQLException ex){
                    System.out.println(ex);
                }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }






        return result1 && result2;
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


