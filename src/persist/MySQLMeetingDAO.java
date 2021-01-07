package persist;

import core.Meeting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implements MeetingDAO and implements methods to manipulate Meeting related persistent data from a MySQL database.
 */
public class MySQLMeetingDAO implements MeetingDAO{

    /**
     * Connection to the database.
     */
    private final Connection connection;

    /**
     * TopicDAO used to manipulate topic related persistent data.
     */
    private final TopicDAO topicDAO;

    /**
     * UserDAO used to manipulate user related persistent data.
     */
    private final UserDAO userDAO;

    /**
     * Constructs the MySQLMeetingDAO.
     */
    public MySQLMeetingDAO(){
        ConnectionDBMySQL instanceConnection = ConnectionDBMySQL.getInstance();
        this.connection = instanceConnection.getConnection();
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
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
    public boolean insertMeetingWithRoom(int idMeeting, String nameRoom) {
        boolean result = false;

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into meetingWithRoom (idMeeting, nameRoom) values('" + idMeeting + "','" + nameRoom + "');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertWaitingMeetingWithRoom(int idMeeting, String nameRoom) {
        boolean result = false;

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into waiting_meetingWithRoom (idMeeting, nameRoom) values('" + idMeeting + "','" + nameRoom + "');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertAndGetId(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic) {

        int id = -1;
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into meetings (dateBegin, hourBegin, dateEnd, hourEnd, userCreator, topic) values('" + dateBegin + "','" + hourBegin + "','" + dateEnd + "','" + hourEnd +"','"+ clientMeeting +"', '"+ meetingTopic +"');", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    @Override
    public int insertWaitingMeetingAndGetId(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic) {
        int id = -1;
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into waiting_meetings (dateBegin, hourBegin, dateEnd, hourEnd, userCreator, topic) values('" + dateBegin + "','" + hourBegin + "','" + dateEnd + "','" + hourEnd +"','"+ clientMeeting +"', '"+ meetingTopic +"');", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    @Override
    public Meeting findWaitingMeetingByID(Integer id){
        Meeting m = new Meeting();

        try{

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from waiting_meetings where id = " + id + ";");

            if(rs.next()){
                m.setId(rs.getInt(1));
                LocalDate dateBegin = rs.getDate(2).toLocalDate();
                m.setDateBegin(dateBegin);
                LocalTime hourBegin = rs.getTime(3).toLocalTime();
                m.setHourBegin(hourBegin);
                LocalDate dateEnd = rs.getDate(4).toLocalDate();
                m.setDateEnd(dateEnd);
                LocalTime hourEnd = rs.getTime(5).toLocalTime();
                m.setHourEnd(hourEnd);
                System.out.println(m.getId() + " " + m.getDateBegin() + " " + m.getHourBegin() + " " + m.getDateEnd() + " " + m.getHourEnd());
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
            ResultSet rs = stmt.executeQuery("select * from meetings where id = " + id + ";");

            if(rs.next()){
                m.setId(rs.getInt(1));
                LocalDate dateBegin = rs.getDate(2).toLocalDate();
                m.setDateBegin(dateBegin);
                LocalTime hourBegin = rs.getTime(3).toLocalTime();
                m.setHourBegin(hourBegin);
                LocalDate dateEnd = rs.getDate(4).toLocalDate();
                m.setDateEnd(dateEnd);
                LocalTime hourEnd = rs.getTime(5).toLocalTime();
                m.setHourEnd(hourEnd);
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
    public HashMap<Integer, String> findAllWaitingMeetings() {

        HashMap<Integer,String> res = new HashMap<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,userCreator FROM waiting_meetings;");
            while(rs.next()){
                String str = rs.getString(2) + " " + rs.getInt(1);
                res.put(rs.getInt(1), str);
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
                m.setDateEnd(dateEnd);
                LocalTime hourEnd = rs.getTime(5).toLocalTime();
                m.setHourEnd(hourEnd);
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
                    System.out.println(ex.getSQLState());
                }
            } catch (SQLException ex){
                System.out.println(ex.getSQLState());
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
            System.out.println(ex.getSQLState());
        }

        return result;
    }

    @Override
    public void deleteMeeting(int id, boolean manager) {

        String str;

        if(manager){
            str = "meetings";
        }
        else {
            str = "waiting_meetings";
        }

        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM " + str +" where id = '"+id+"';");
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
