package persist;

import core.Admin;
import core.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAdminDAO implements AdminDAO {

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLAdminDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public Admin findByUsername(String username) {
        Admin a = new Admin();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from admins where username = '" + username + "';");
            if(rs.next()){
                a.setUsername(rs.getString(1));
                a.setPassword(rs.getString(2));
                a.setFirstName(rs.getString(3));
                a.setLastName(rs.getString(4));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return a;
    }

    @Override
    public boolean insertAdmin(String username, String first, String last, String mdp) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into admins (username,password,firstname,lastname) values('" + username + "','" + mdp + "','" + first + "','" + last +"');");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public boolean deleteAdmin(String username){
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM admins WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return result;
    }
}
