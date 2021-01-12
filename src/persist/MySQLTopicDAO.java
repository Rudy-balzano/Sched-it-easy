package persist;

import core.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class implements the interface TopicDAO and implements methods to manipulate persistent data from a MySQL database.
 */
public class MySQLTopicDAO implements TopicDAO {

    /**
     * Connection to the database.
     */
    private final Connection connection;

    /**
     * Constructs the MySQLTopicDAO.
     */
    public MySQLTopicDAO(){
        ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public boolean insert(String nameTopic, String descriptionTopic){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into topics (nameTopic,description) values('" + nameTopic + "','" + descriptionTopic + "');");
            result = true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean update(String name, String description) {
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE topics SET description = '" + description + "' WHERE nameTopic = '" + name + "';");
            result = true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String name) {
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM topics WHERE nameTopic = '" + name+ "';");
            result = true;
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Topic findBy(String name) {
        Topic t = null;
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM topics WHERE nameTopic = '" + name + "';");
            if(rs.next()){
                t = new Topic(rs.getString(1),rs.getString(2));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return t;
    }

    @Override
    public ArrayList<Topic> findAll() {
        ArrayList<Topic> topics = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM topics");
            while(rs.next()){
                Topic t = new Topic(rs.getString(1),rs.getString(2));
                topics.add(t);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return topics;
    }

}
