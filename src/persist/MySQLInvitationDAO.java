package persist;

import core.Invitation;
import core.Meeting;
import core.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


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
    public boolean insert(String invitedUser, int state, int meetingInvitation) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into invitations (invitedUsername, state, idMeetingInvitation) values('" + invitedUser + "','" + state + "','" + meetingInvitation + "');");
            result = true;
        } catch (SQLException ex) {
            System.out.println(ex);
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
            System.out.println(ex);
        }

        return result;
    }

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
            System.out.println("SQL request error");
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
                System.out.println(i.getMeetingInvitation().getClientMeeting());
                res.add(i);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return res;
    }
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
                System.out.println(i.getMeetingInvitation().getClientMeeting());
                res.add(i);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return res;
    }

}
