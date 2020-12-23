package persist;

import core.Topic;

import java.sql.*;
import java.util.Date;

public class MySQLMeetingDAO implements MeetingDAO{

    private static int id=1;
    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLMeetingDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public boolean insert(String topicName, Date date, int duration) {

        boolean result = false;
        

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into meetings (id,topic,date,duration) values('" + id + "','" + topicName + "','" + date + "','" + duration + "','" + "');");
            this.id++;
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
