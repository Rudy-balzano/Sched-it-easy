package persist;

import core.Group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class MySQLGroupDAO implements GroupDAO{

    private final Connection connection;

    public MySQLGroupDAO(){
        ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public ArrayList<String> findAll() {

        ArrayList<String> groups = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from groups;");

            while(rs.next()){
                groups.add(rs.getString(1));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return groups;
    }

    @Override
    public Group findByName(String name) {

        Group group = new Group();
        group.setNameGroup(name);

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from groupMembers where groupname = '" + name + "';");

            while(rs.next()){
                group.getUsers().add(rs.getString(2));
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return group;
    }

    @Override
    public boolean insert(String name) {

        boolean result = false;

        if(verify(name)){
            try{
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("insert into groups (name) values('" +name+"');");
                result = true;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }

        return result;
    }

    private boolean verify(String name){
        boolean exist = false;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from groups where name = '" + name + "';");
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return !exist;
    }

    @Override
    public boolean delete(String name) {

        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM groups WHERE name = '" + name + "';");
            result = true;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addMember(String username, String groupName) {

        boolean result = false;

        if(!verifyUsername(username, groupName)){
            try{
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("insert into groupMembers (groupname,username) values('" +groupName+"','"+username+"');");
                result = true;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public boolean deleteMember(String username, String groupName) {

        boolean result = false;
        if(verifyUsername(username, groupName)){
            try{
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("DELETE FROM groupMembers where username = '" + username + "' and groupname = '"+groupName+"';");
                result = true;
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }

        return result;
    }

    private boolean verifyUsername(String username, String groupName){

        boolean exist = false;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from groupMembers where username = '" + username + "' and groupname = '"+groupName+"';");
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return exist;
    }
}
