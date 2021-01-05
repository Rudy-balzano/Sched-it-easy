package persist;

import core.Invitation;
import core.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public interface MeetingDAO {

    boolean insert(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    boolean insertWaitingMeeting(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    int insertAndGetId (LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    int insertWaitingMeetingAndGetId(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    ArrayList<Meeting> findAllForUsername(String username);

    Meeting findWaitingMeetingByID(Integer id);

    Meeting findByID(Integer id);

    HashMap<Integer,String> findAllWaitingMeetings();

    boolean validateMeeting(int id);

    boolean declineWaitingMeeting(int id);

}
