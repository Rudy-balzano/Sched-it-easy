package persist;
import java.sql.Connection;
import core.Topic;
import java.sql.*;

public class MySQLTopicDAO implements TopicDAO {
    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLTopicDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    public boolean insertTopic(String nameTopic, String descriptionTopic){
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into topics (nameTopic,descriptionTopic) values('" + nameTopic + "','" + descriptionTopic + "');");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public boolean insert(String nameTopic, String descriptionTopic) {
        boolean result = false;
        insertTopic(nameTopic, descriptionTopic);
        result = true;
        return result;
    }

    @Override
    public boolean update(String name, String description) {
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE topics SET descriptionTopic = " + description + "WHERE nameTopic = " + name);
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public boolean delete(String name) {
        boolean result = false;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM topics WHERE nameTopic = " + name);
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public Topic findBy(String name) {
        Topic t = null;
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM topics WHERE nameTopic = " + name);
            if(rs.next()){
                t = new Topic(rs.getString(1),rs.getString(2))
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        return t;
    }

}
