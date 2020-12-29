package persist;

import core.Meeting;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
    public boolean insert(String topicName, Date dateDebut, Date dateFin, String meetingCreator) {

        boolean result = false;
        

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into meetings (topic,date_begin,date_end,meetingCreator) values('" + topicName + "','" + dateDebut + "','" + dateFin + "','" + meetingCreator +"');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertWaitingMeeting(String topicName, Date dateDebut,Date dateFin, String meetingCreator) {
        boolean result = false;


        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into waitingMeetings (topic,date_begin,date_end,meetingCreator) values('" + topicName + "','" + dateDebut + "','" + dateFin + "','" + meetingCreator +"');");
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Meeting findByID(Integer id){
        Meeting m = new Meeting();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from meetings where id = '" + id + "';");

            if(rs.next()){
                m.setId(rs.getInt(1));
                m.setMeetingTopic(topicDAO.findBy(rs.getString(2)));
                m.setDateDebut(rs.getDate(3));
                m.setDateFin(rs.getDate(4));
                m.setClientMeeting(userDAO.findByUsername((rs.getString(5))));
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
}
