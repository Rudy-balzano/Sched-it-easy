package core;

import java.util.Date;
import java.util.ArrayList;

public class Meeting {
    private Date date;
    private int duration;
    private User clientMeeting;
    private ArrayList<Invitation> listInvitation = new ArrayList<Invitation> ();
    //private Topic meetingTopic;

    public Meeting(Date date,int duration,User clientMeeting) {

        this.clientMeeting = clientMeeting;
        this.date = date;
        this.duration = duration;
        this.listInvitation = listInvitation;
        //this.meetingTopic = meetingTopic

    }

    //Getters and setters

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public User getClientMeeting() {
        return clientMeeting;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setClientMeeting(User clientMeeting) {
        this.clientMeeting = clientMeeting;
    }

    public ArrayList<Invitation> getListInvitation() {
        return listInvitation;
    }

    public void setListInvitation(ArrayList<Invitation> listInvitation) {
        this.listInvitation = listInvitation;
    }
}
