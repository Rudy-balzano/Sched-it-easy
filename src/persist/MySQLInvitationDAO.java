package persist;

import core.Meeting;
import core.User;

import java.sql.Connection;

public class MySQLInvitationDAO implements InvitationDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;

    public MySQLInvitationDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
    }

    @Override
    public boolean insert(User invitedUser, int state, Meeting meetingInvitation) {
        return false;
    }
}
