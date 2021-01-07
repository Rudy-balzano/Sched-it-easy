package persist;

import core.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface implements the method used by an MeetingDAO to manipulates meeting related persistent data.
 */
public interface MeetingDAO {
    /**
     * Makes a new meeting persistent.
     * @param dateBegin meeting's beginning date.
     * @param hourBegin meeting's beginning hour.
     * @param dateEnd meeting's ending date.
     * @param hourEnd meeting's ending hour.
     * @param clientMeeting the meeting's creator.
     * @param meetingTopic the meeting's related topic.
     * @return boolean that indicates if the operation was successful.
     */
    boolean insert(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    /**
     * Makes a new WaitingMeeting persistent.
     * @param dateBegin meeting's beginning date.
     * @param hourBegin meeting's beginning hour.
     * @param dateEnd meeting's ending date.
     * @param hourEnd meeting's ending hour.
     * @param clientMeeting the meeting's creator.
     * @param meetingTopic the meeting's related topic.
     * @return boolean that indicates if the operation was successful.
     */
    boolean insertWaitingMeeting(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    /**
     * Makes a new MeetingWithRoom persistent.
     * @param idMeeting related meeting's id.
     * @param nameRoom related room's name.
     * @return boolean that indicates if the operation was successful.
     */
    boolean insertMeetingWithRoom(int idMeeting, String nameRoom);

    /**
     * Makes a new WaitingMeetingWithRoom persistent.
     * @param idMeeting related meeting's id.
     * @param nameRoom related room's name.
     * @return boolean that indicates if the operation was successful.
     */
    boolean insertWaitingMeetingWithRoom(int idMeeting, String nameRoom);

    /**
     * Makes a new meeting persistent and returns it's id.
     * @param dateBegin meeting's beginning date.
     * @param hourBegin meeting's beginning hour.
     * @param dateEnd meeting's ending date.
     * @param hourEnd meeting's ending hour.
     * @param clientMeeting the meeting's creator.
     * @param meetingTopic the meeting's related topic.
     * @return the newly created Meeting's id.
     */
    int insertAndGetId (LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    /**
     * Makes a new WaitingMeeting persistent and returns it's id.
     * @param dateBegin meeting's beginning date.
     * @param hourBegin meeting's beginning hour.
     * @param dateEnd meeting's ending date.
     * @param hourEnd meeting's ending hour.
     * @param clientMeeting the meeting's creator.
     * @param meetingTopic the meeting's related topic.
     * @return the newly created WaitingMeeting's id.
     */
    int insertWaitingMeetingAndGetId(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, String meetingTopic);

    /**
     * Searches into the database all the Meetings that the given user is invited to.
     * @param username the user's username.
     * @return a collection of meetings.
     */
    ArrayList<Meeting> findAllForUsername(String username);

    /**
     * Retrieves a WaitingMeeting given its id.
     * @param id the WaitingMeeting's id.
     * @return the corresponding Meeting.
     */
    Meeting findWaitingMeetingByID(Integer id);

    /**
     * Retrieves a Meeting given its id.
     * @param id the Meeting's id.
     * @return the corresponding Meeting.
     */
    Meeting findByID(Integer id);

    /**
     * Searches into the database all the WaitingMeetings that it contains.
     * @return a HashMap that associates the WaitingMeeting's id and its creator's username.
     */
    HashMap<Integer,String> findAllWaitingMeetings();

    /**
     * Is to be used by managers. Validate a WaitingMeeting and creates a Meeting from it.
     * @param id the given WaitingMeeting's id.
     * @return a boolean that indicates if the operation was successful.
     */
    boolean validateMeeting(int id);

    /**
     * Is to be used by managers. Decline a WaitingMeeting and deletes it from the database.
     * @param id the given WaitingMeeting's id.
     * @return a boolean that indicates if the operation was successful.
     */
    boolean declineWaitingMeeting(int id);

    /**
     * If used by a Manager : deletes the given id's matching Meeting
     * If used by a regular Client : deletes the given id's matching WaitingMeeting
     * @param id the meeting's id.
     * @param manager indicates if the user is a manager or not.
     */
    void deleteMeeting(int id, boolean manager);

}
