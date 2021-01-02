package persist;

import core.Meeting;
import core.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class MySQLMeetingDAO implements MeetingDAO{

    private ConnectionDBMySQL instanceConnection;
    private Connection connection;
    private FactoryDAOImpl factoryDAO;
    private TopicDAO topicDAO;
    private UserDAO userDAO;

    public MySQLMeetingDAO(){
        this.instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.topicDAO = factoryDAO.createTopicDAO();
        this.userDAO = factoryDAO.createUserDAO();

    }

    @Override
    public boolean insert(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic) {

        boolean result = false;
        

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into meetings (dateBegin, hourBegin, dateEnd, hourEnd, userCreator, topic) values('" + dateBegin + "','" + hourBegin + "','" + dateEnd + "','" + hourEnd +"','"+ clientMeeting +"', '"+ meetingTopic +"');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertWaitingMeeting(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic) {

        boolean result = false;

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into waiting_meetings (dateBegin, hourBegin, dateEnd, hourEnd, userCreator, topic) values('" + dateBegin + "','" + hourBegin + "','" + dateEnd + "','" + hourEnd +"','"+ clientMeeting +"', '"+ meetingTopic +"');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Meeting findWaitingMeetingByID(Integer id){
        Meeting m = new Meeting();

        try{

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from waiting_meetings where id = '" + id + "';");

            if(rs.next()){
                m.setId(rs.getInt(1));
                LocalDate dateBegin = rs.getDate(2).toLocalDate();
                m.setDateBegin(dateBegin);
                LocalTime hourBegin = rs.getTime(3).toLocalTime();
                m.setHourBegin(hourBegin);
                LocalDate dateEnd = rs.getDate(4).toLocalDate();
                m.setDateBegin(dateEnd);
                LocalTime hourEnd = rs.getTime(5).toLocalTime();
                m.setHourBegin(hourEnd);
                m.setClientMeeting(userDAO.findByUsername((rs.getString(6))).getUserName());
                m.setMeetingTopic(topicDAO.findBy(rs.getString(7)));
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return m;
    }

    @Override
    public Meeting findByID(Integer id){
        Meeting m = new Meeting();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from meetings where id = '" + id + "';");

            if(rs.next()){
                m.setId(rs.getInt(1));
                LocalDate dateBegin = rs.getDate(2).toLocalDate();
                m.setDateBegin(dateBegin);
                LocalTime hourBegin = rs.getTime(3).toLocalTime();
                m.setHourBegin(hourBegin);
                LocalDate dateEnd = rs.getDate(4).toLocalDate();
                m.setDateBegin(dateEnd);
                LocalTime hourEnd = rs.getTime(5).toLocalTime();
                m.setHourBegin(hourEnd);
                m.setClientMeeting(userDAO.findByUsername((rs.getString(6))).getUserName());
                m.setMeetingTopic(topicDAO.findBy(rs.getString(7)));
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return m;
    }

    @Override
    public ArrayList<Meeting> findAllForUsername(String username) {

        ArrayList<Meeting> listMeetings = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from invitations where invitedUsername = '" + username + "';");

            while(rs.next()){
                listMeetings.add(findByID(rs.getInt(3)));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return listMeetings;
    }

    @Override
    public HashMap<String, Integer> findAllWaitingMeetings() {

        HashMap<String,Integer> res = new HashMap<String,Integer>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,userCreator FROM waiting_meetings;");
            while(rs.next()){
                res.put(rs.getString(2), rs.getInt(1));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean validateMeeting(int id) {
        boolean result1 = false;
        boolean result2 = false;

        Meeting m = new Meeting();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from waiting_meetings where id = '" + id + "';");
            if(rs.next()) {
                m.setId(rs.getInt(1));
                LocalDate dateBegin = rs.getDate(2).toLocalDate();
                m.setDateBegin(dateBegin);
                LocalTime hourBegin = rs.getTime(3).toLocalTime();
                m.setHourBegin(hourBegin);
                LocalDate dateEnd = rs.getDate(4).toLocalDate();
                m.setDateBegin(dateEnd);
                LocalTime hourEnd = rs.getTime(5).toLocalTime();
                m.setHourBegin(hourEnd);
                m.setClientMeeting(userDAO.findByUsername((rs.getString(6))).getUserName());
                m.setMeetingTopic(topicDAO.findBy(rs.getString(7)));
            }

            try{
                Statement stmt1 = connection.createStatement();
                stmt1.executeUpdate("insert into meetings (dateBegin, hourBegin, dateEnd, hourEnd, userCreator, topic) values('" + m.getDateBegin() + "','" + m.getHourBegin() + "','" + m.getDateEnd() + "','" + m.getHourEnd() +"','"+ m.getClientMeeting() +"', '"+ m.getMeetingTopic() +"');");
                result1 = true;

                try{
                    Statement stmt2 = connection.createStatement();
                    stmt2.executeUpdate("DELETE FROM waiting_meetings WHERE id = '" + id + "';");
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

    @Override
    public boolean declineWaitingMeeting(int id) {
        boolean result = false;

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM waiting_meetings WHERE id = '" + id + "';");
            result = true;
        } catch (SQLException ex){
            System.out.println(ex);
        }

        return result;
    }
}
