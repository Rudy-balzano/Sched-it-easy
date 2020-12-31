package persist;

import core.Meeting;
import core.User;

import java.sql.*;
import java.time.LocalDate;
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

    public HashMap<String, Integer> findAllWaitingMeetings() {

        HashMap<String,Integer> res = new HashMap<String,Integer>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT meetingCreator,date_begin,date_end FROM waiting_meetings,id;");
            while(rs.next()){
                String str = rs.getString(1)+" : " + "la date de debut du meeting est :" + rs.getDate(3)+ " : "+ "La date de fin est"+ rs.getDate(4);
                res.put(str, rs.getInt(2));
            }
        } catch (SQLException ex){
            System.out.println("SQL request error");
        }
        return res;
    }
    public boolean validateMeeting(Integer id) {
        boolean result1 = false;
        boolean result2 = false;

        Meeting m = new Meeting();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from waiting_meetings where id = '" + id + "';");
            if(rs.next()) {
                m.setId(rs.getInt(5));
                m.setDateDebut(rs.getDate(1));
                m.setDateFin(rs.getDate(2));
                m.setMeetingTopic(topicDAO.findBy(rs.getString(4)));
                m.setClientMeeting(userDAO.findByUsername(rs.getString(3)));
            }

            try{
                Statement stmt1 = connection.createStatement();
                stmt1.executeUpdate("insert into meetings (dateDebut,dateFin,clientMeeting, meetingTopic,id) values('" + m.getDateDebut() + "','" + m.getDateFin() + "','" + m.getClientMeeting()  + "','" + m.getMeetingTopic() +"','"+ m.getId() + "');");
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
}
