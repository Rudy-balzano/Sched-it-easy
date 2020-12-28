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
}
