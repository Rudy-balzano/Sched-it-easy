package core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Class that represent a meeting
 * @author emilie jean
 * @version 1.0
 */
public class Meeting {
    /**
     * Unique id of the meeting
     * @see Meeting#getId()
     * @see Meeting#setId(int)
     */
    private int id;
    /**
     * Date where the Meeting Begins
     * @see Meeting#getDateBegin()
     * @see Meeting#setDateBegin(LocalDate)
     */
    private LocalDate dateBegin;
    /**
     * Hour where the Meeting begins
     * @see Meeting#setHourBegin(LocalTime)
     * @see Meeting#getHourBegin()
     */
    private LocalTime hourBegin;
    /**
     * Date where the Meeting ends
     * @see Meeting#getDateEnd()
     * @see Meeting#setDateEnd(LocalDate)
     */
    private LocalDate dateEnd;
    /**
     * Hour where the Meeting ends
     * @see Meeting#setHourEnd(LocalTime)
     * @see Meeting#getHourEnd()
     */
    private LocalTime hourEnd;
    /**
     * Client that has created the meeting
     * @see Meeting#getClientMeeting()
     * @see Meeting#setClientMeeting(String)
     */
    private String clientMeeting;
    /**
     * Topic that the meeting is about
     * @see Meeting#getMeetingTopic()
     * @see Meeting#setMeetingTopic(Topic)
     */
    private Topic meetingTopic;
    /**
     * List of all the invitations for this meeting
     * @see Meeting#getListInvitation()
     * @see Meeting#setListInvitation(ArrayList)
     */
    private ArrayList<Invitation> listInvitation;

    /**
     * Constructor of Meeting
     * @param id
     * @param dateBegin
     * @param hourBegin
     * @param dateEnd
     * @param hourEnd
     * @param clientMeeting
     * @param meetingTopic
     * @param listInvitation
     * @see Meeting#id
     * @see Meeting#dateBegin
     * @see Meeting#dateEnd
     * @see Meeting#hourBegin
     * @see Meeting#hourEnd
     * @see Meeting#clientMeeting
     * @see Meeting#meetingTopic
     * @see Meeting#listInvitation
     */
    public Meeting(int id, LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String clientMeeting, Topic meetingTopic, ArrayList<Invitation> listInvitation) {

        this.id = id;
        this.dateBegin = dateBegin;
        this.hourBegin = hourBegin;
        this.dateEnd = dateEnd;
        this.hourEnd = hourEnd;
        this.clientMeeting = clientMeeting;
        this.meetingTopic = meetingTopic;
        this.listInvitation = listInvitation;

    }

    /**
     * Constructor where everything is initialized to null
     */
    public Meeting(){
        this.dateBegin = null;
        this.hourBegin = null;
        this.dateEnd = null;
        this.hourEnd = null;
        this.clientMeeting = null;
        this.meetingTopic = null;
        this.listInvitation = null;
    }

    //Getters and setters


    /**
     * Return the id of the meeting
     * @return the id of the meeting
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the meeting
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the date where the meeting begins
     * @return the date where the meeting begins
     */
    public LocalDate getDateBegin() {
        return dateBegin;
    }

    /**
     * Set the date where the meeting begins
     * @param dateBegin
     */
    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    /**
     * Return the Hour where the meeting begins
     * @return the Hour where the meeting begins
     */
    public LocalTime getHourBegin() {
        return hourBegin;
    }

    /**
     * Set the Hour where the meeting begins
     * @param hourBegin
     */
    public void setHourBegin(LocalTime hourBegin) {
        this.hourBegin = hourBegin;
    }

    /**
     * Return the date where the meeting ends
     * @return the date where the meeting ends
     */
    public LocalDate getDateEnd() {
        return dateEnd;
    }

    /**
     * Set the date where the meeting ends
     * @param dateEnd
     */
    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Return the hour where the meeting ends
     * @return the hour where the meeting ends
     */
    public LocalTime getHourEnd() {
        return hourEnd;
    }

    /**
     * Set the hour where the meeting ends
     * @param hourEnd
     */
    public void setHourEnd(LocalTime hourEnd) {
        this.hourEnd = hourEnd;
    }

    /**
     * Return the Client that has created this meeting
     * @return the Client that has created this meeting
     */
    public String getClientMeeting() {
        return clientMeeting;
    }

    /**
     * Set the Client that has created this meeting
     * @param clientMeeting
     */
    public void setClientMeeting(String clientMeeting) {
        this.clientMeeting = clientMeeting;
    }

    /**
     * Return the topic of this meeting
     * @return the topic of this meeting
     */
    public Topic getMeetingTopic() {
        return meetingTopic;
    }

    /**
     * Set the topic of this meeting
     * @param meetingTopic
     */
    public void setMeetingTopic(Topic meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    /**
     * Return the list of all the invitations for this meeting
     * @return the list of all the invitations for this meeting
     */
    public ArrayList<Invitation> getListInvitation() {
        return listInvitation;
    }

    /**
     * Set the list of all the invitations for this meeting
     * @param listInvitation
     */
    public void setListInvitation(ArrayList<Invitation> listInvitation) {
        this.listInvitation = listInvitation;
    }
}
