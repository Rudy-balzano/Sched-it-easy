package persist;

import core.Invitation;
import core.Meeting;
import core.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements the interface InvitationDAO and implements methods to manipulate Invitation related persistent data from a MySQL database.
 */
public class MySQLInvitationDAO implements InvitationDAO{

    /**
     * Connection to the database
     */
    private final Connection connection;

    /**
     * MeetingDAO used to manipulate Meeting related persistent data.
     */
    private final MeetingDAO meetingDAO;

    /**
     * UserDAO used to manipulate User related persistent data.
     */
    private final UserDAO userDAO;

    /**
     * Constructs a MySQLInvitationDAO.
     */
    public MySQLInvitationDAO(){
        ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.meetingDAO = factoryDAO.createMeetingDAO();
        this.userDAO = factoryDAO.createUserDAO();
    }

    @Override
    public boolean insert(String invitedUser, int state, int meetingInvitation) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into invitations (invitedUsername, state, idMeetingInvitation) values('" + invitedUser + "','" + state + "','" + meetingInvitation + "');");
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean insertInvitationGroup(String groupName, int meetingInvitation) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into invitationGroups (groupName, idMeeting) values('" + groupName + "','" + meetingInvitation + "');");
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public Invitation findBy(User invitedUSer, Meeting meetingInvitation ){
        Invitation i = new Invitation();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from invitations where invitedUsername = '" + invitedUSer.getUserName() + "' and idMeetingInvitation =  '" + meetingInvitation.getId()+ "' and state = 0;");
            if(rs.next()){
                i.setInvitedUser(userDAO.findByUsername(rs.getString(1)));
                i.setState(rs.getInt(2));
                i.setMeetingInvitation(meetingDAO.findByID(rs.getInt(3)));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return i;
    }

    @Override
    public Collection<String> getInvitedUsers(int idMeeting) {

        Collection<String> invitedUsers = new ArrayList<>() ;

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select invitedUsername  from invitations where idMeetingInvitation = '" + idMeeting +"';");
            while(rs.next()){
                invitedUsers.add(rs.getString(1));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }


        return invitedUsers;
    }

    @Override
    public ArrayList<String> getInvitedGroups(int idMeeting) {
        ArrayList<String> invitedGroups = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select groupName  from invitationGroups where idMeeting = '" + idMeeting +"';");
            while(rs.next()){
                invitedGroups.add(rs.getString(1));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return invitedGroups;

    }

    @Override
    public void setPaid(int idMeeting, String username) {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE rent_equipments SET paid = 1 where idMeeting = "+idMeeting+" and username = '"+username+"';");
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<Invitation> findAllInvitation(String username){
        ArrayList<Invitation> res = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM invitations WHERE invitedUsername = '"+ username+ "' and state = 0;");
            while(rs.next()){
                Invitation i = new Invitation();
                i.setInvitedUser(userDAO.findByUsername(rs.getString(1)));
                i.setState(rs.getInt(2));
                i.setMeetingInvitation(meetingDAO.findByID(rs.getInt(3)));
                res.add(i);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<Invitation> findInvitation(String username){
        ArrayList<Invitation> res = new ArrayList<>() {
        };

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM invitations WHERE invitedUsername = '"+ username+ "' and state <> -1;");
            while(rs.next()){
                Invitation i = new Invitation();
                i.setInvitedUser(userDAO.findByUsername(rs.getString(1)));
                i.setState(rs.getInt(2));
                i.setMeetingInvitation(meetingDAO.findByID(rs.getInt(3)));
                res.add(i);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return res;
    }



}
