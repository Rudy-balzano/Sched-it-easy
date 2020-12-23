package persist;

import core.Meeting;
import core.User;

import java.sql.*;


public class MySQLInvitationDAO implements InvitationDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLInvitationDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public boolean insert(User invitedUser, int state, Meeting meetingInvitation) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into invitation (invitedUsername, state, idMeetingInvitaion) values('" + invitedUser.getUserName() + "','" + state + "','" + meetingInvitation.getId() + "');");
            result = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return result;
    }
}
