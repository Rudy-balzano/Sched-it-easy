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
    public boolean insert(Topic topic, Date date, int duration) {
        return false;
    }
}
