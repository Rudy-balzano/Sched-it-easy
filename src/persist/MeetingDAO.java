package persist;

import core.Meeting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public interface MeetingDAO {

    public boolean insert(String topicName, Date dateDebut, Date dateFin, String meetingCreator);

    public  boolean insertWaitingMeeting(String topicName, Date dateDebut, Date dateFin, String meetingCreator);

    public ArrayList<Meeting> findAllForUsername(String username);

    public Meeting findByID(Integer id);
}
