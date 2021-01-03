package core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Meeting {
    private int id;
    private LocalDate dateBegin;
    private LocalTime hourBegin;
    private LocalDate dateEnd;
    private LocalTime hourEnd;
    private String clientMeeting;
    private Topic meetingTopic;
    private ArrayList<Invitation> listInvitation;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalTime getHourBegin() {
        return hourBegin;
    }

    public void setHourBegin(LocalTime hourBegin) {
        this.hourBegin = hourBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalTime getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(LocalTime hourEnd) {
        this.hourEnd = hourEnd;
    }

    public String getClientMeeting() {
        return clientMeeting;
    }

    public void setClientMeeting(String clientMeeting) {
        this.clientMeeting = clientMeeting;
    }

    public Topic getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(Topic meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public ArrayList<Invitation> getListInvitation() {
        return listInvitation;
    }

    public void setListInvitation(ArrayList<Invitation> listInvitation) {
        this.listInvitation = listInvitation;
    }
}
