package persist;

import core.Invitation;
import core.Meeting;
import core.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;


public class MySQLInvitationDAO implements InvitationDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;
    private FactoryDAOImpl factoryDAO;
    private MeetingDAO meetingDAO;
    private UserDAO userDAO;

    public MySQLInvitationDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.meetingDAO = factoryDAO.createMeetingDAO();
        this.userDAO = factoryDAO.createUserDAO();
    }

    @Override
    public boolean insert(User invitedUser, int state, Meeting meetingInvitation) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into invitation (invitedUsername, state, idMeetingInvitation) values('" + invitedUser.getUserName() + "','" + state + "','" + meetingInvitation.getId() + "');");
            result = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return result;
    }
    public Invitation findBy(User invitedUSer, Meeting meetingInvitation ){
        Invitation i = new Invitation();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from invitations where invitedUsername = '" + invitedUSer.getUserName() + "' and idMeetingInvitation =  '" + meetingInvitation.getId()+ "';");
            if(rs.next()){
                i.setInvitedUser(userDAO.findByUsername(rs.getString(1)));
                i.setState(rs.getInt(2));
                i.setMeetingInvitation(meetingDAO.findByID(rs.getInt(3)));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return i;
    }
    public ArrayList<Invitation> findAllInvitation(String username){
        ArrayList<Invitation> res = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM invitations WHERE invitedUsername = '"+ username+ "';");
            while(rs.next()){
                Invitation i = new Invitation();
                i.setInvitedUser(userDAO.findByUsername(rs.getString(1)));
                i.setState(rs.getInt(2));
                i.setMeetingInvitation(meetingDAO.findByID(rs.getInt(3)));
                System.out.println(i.getMeetingInvitation().getClientMeeting());
                res.add(i);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return res;
    }
}
