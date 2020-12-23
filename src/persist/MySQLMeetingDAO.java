package persist;

import core.Topic;

import java.sql.*;
import java.util.Date;

public class MySQLMeetingDAO implements MeetingDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLMeetingDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public boolean insert(String topicName, Date date, int duration, String meetingCreator) {

        boolean result = false;
        

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into meetings (topic,date,duration,meetingCreator) values('" + topicName + "','" + date + "','" + duration + "','" + meetingCreator +"');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
