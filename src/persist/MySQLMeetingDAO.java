package persist;

import core.Topic;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class MySQLMeetingDAO implements MeetingDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLMeetingDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public boolean insert(String topicName, LocalDate date, String time,  String duration, String meetingCreator) {

        boolean result = false;
        

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into meetings (topic,date,time,duration,meetingCreator) values('" + topicName + "','" + date + "','" + time + "','" + duration + "','" + meetingCreator +"');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
