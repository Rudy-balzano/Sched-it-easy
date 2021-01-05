package persist;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import core.Invitation;
import core.Meeting;
import core.User;

public class MySQLUserDAO implements UserDAO {

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;
    private FactoryDAOImpl factoryDAO;
    private TopicDAO topicDAO;

    public MySQLUserDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.topicDAO = factoryDAO.createTopicDAO();
    }

    @Override
    public Collection<String> findAllRegUsersNames() {
        Collection<String> res = new ArrayList<String>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT firstname,lastname,username FROM users WHERE isManager = 0;");
            while(rs.next()){
                //Concatenate first and last names
                String name = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
                res.add(name);
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return res;
    }
    public Collection<Meeting> findSchedule(String username) {
        Collection<Meeting> m = new ArrayList<Meeting>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, idMeeting FROM meetingAttendence WHERE username = '" + username + "';");
            while (rs.next()) {
                int idm = rs.getInt(2);
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM meetings WHERE id = ?;");
                ps.setInt(1,idm);
                ResultSet me = ps.executeQuery();
                while(me.next()) {
                    Meeting meeting = new Meeting();

                    meeting.setId(me.getInt(1));
                    LocalDate dateBegin = me.getDate(2).toLocalDate();
                    meeting.setDateBegin(dateBegin);
                    LocalTime hourBegin = this.timeToLocalDate(me.getTime(3));
                    meeting.setHourBegin(hourBegin);
                    LocalDate dateEnd = me.getDate(4).toLocalDate();
                    meeting.setDateEnd(dateEnd);
                    LocalTime hourEnd = this.timeToLocalDate(me.getTime(5));
                    meeting.setHourEnd(hourEnd);
                    meeting.setClientMeeting(findByUsername((me.getString(6))).getUserName());
                    meeting.setMeetingTopic(topicDAO.findBy(me.getString(7)));
                    m.add(meeting);
                }
            }
            } catch(SQLException ex){
                System.out.println("SQL request error");
                ex.printStackTrace();
            }
            return m;
        }

    @Override
    public boolean modifyUser(String username,String firstName, String lastName) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE users SET firstname = '" + firstName +"', lastname = '" + lastName +"' WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }

    @Override
    public Collection<String> findAllManagersNames() {
        Collection<String> res = new ArrayList<String>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT firstname,lastname,username FROM users WHERE isManager = 1;");
            while(rs.next()){
                //Concatenate first and last names
                String name = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
                res.add(name);
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return res;
    }

    @Override
    public Collection<String> findAllWaitingUsers() {

        Collection<String> res = new ArrayList<String>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username,first_name,last_name FROM waiting_users;");
            while(rs.next()){
                String name = rs.getString(1) + " : " + rs.getString(2) + " " + rs.getString(3);
                res.add(name);
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return res;
    }


    @Override
    public User findByUsername(String username){

        User u = new User();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where username = '" + username + "';");
            if(rs.next()){
                u.setUserName(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setFirstName(rs.getString(3));
                u.setLastName(rs.getString(4));
                u.setIsManager(rs.getBoolean(5));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }

        return u;
    }

    @Override
    public boolean insertWaitingUser(String username, String first, String last, String mdp){
        boolean result = false;

        if(!verify(username)){

            try{
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("insert into waiting_users (username,password,first_name,last_name) values('" + username + "','" + mdp + "','" + first + "','" + last +"');");
                result = true;
            } catch (SQLException ex){
                System.out.println(ex);
            }
        }

        return result;
    }

    @Override
    public boolean makeManager(String username) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE users SET isManager = 1 WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }

    @Override
    public boolean deleteUser(String username) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }
    public boolean declineWaitingUser(String username) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM waiting_users WHERE username = '" + username + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }

public boolean declineWaitingInvitation(String username, int id){
    boolean result = false;

    try{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("update invitations SET state = -1 WHERE invitedUsername = '" + username + "' and idMeetingInvitation = "+id+";");
        result = true;
    } catch (SQLException ex){
        System.out.println(ex);
    }

    return result;
}
    public boolean acceptWaitingInvitation(String username, int id) {
        boolean result = false;

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("update invitations SET state = 1 WHERE invitedUsername = '" + username + "' and idMeetingInvitation = "+id+";");
            result = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return result;
    }



    @Override
    public boolean validateAccount(String username) {
        boolean result1 = false;
        boolean result2 = false;

        User u = new User();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from waiting_users where username = '" + username + "';");
            if(rs.next()) {
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFirstName(rs.getString(4));
                u.setLastName(rs.getString(5));
                u.setIsManager(false);
            }

                try{
                    Statement stmt1 = connection.createStatement();
                    stmt1.executeUpdate("insert into users (username,password,firstname,lastname,isManager) values('" + u.getUserName() + "','" + u.getPassword() + "','" + u.getFirstName() + "','" + u.getLastName() + "','" + 0 +"');");
                    result1 = true;

                    try{
                        Statement stmt2 = connection.createStatement();
                        stmt2.executeUpdate("DELETE FROM waiting_users WHERE username = '" + username + "';");
                        result2 = true;
                    } catch (SQLException ex){
                        System.out.println(ex);
                    }
                } catch (SQLException ex){
                    System.out.println(ex);
                }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }






        return result1 && result2;
    }

    private boolean verify(String username){
        boolean exist = false;

        try {
            Statement stmt1 = connection.createStatement();
            Statement stmt2 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from users where username = '" + username + "';");
            ResultSet rs2 = stmt2.executeQuery("select * from waiting_users where username = '" + username + "';");
            if (rs1.next() || rs2.next()) {
                exist = true;
            }
        } catch (SQLException ex){

            System.out.println(ex);

        }

        return exist;
    }

    private LocalTime timeToLocalDate(Time time){
        return time.toLocalTime().minusHours(1);
    }
}


