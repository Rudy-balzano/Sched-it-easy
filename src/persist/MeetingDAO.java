package persist;

import core.Meeting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public interface MeetingDAO {

    boolean insert(String topicName, Date dateDebut, Date dateFin, String meetingCreator);

    boolean insertWaitingMeeting(String topicName, Date dateDebut, Date dateFin, String meetingCreator);

    ArrayList<Meeting> findAllForUsername(String username);

    Meeting findByID(Integer id);

    HashMap<String,Integer> findAllWaitingMeetings();

    boolean validateMeeting(Integer id);

}
