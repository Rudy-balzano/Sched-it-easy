package core;

import java.util.Date;
import java.util.ArrayList;

public class Meeting {
    private Integer id;
    private Date dateDebut;
    private Date dateFin;
    private User clientMeeting;
    private ArrayList<Invitation> listInvitation;
    private Topic meetingTopic;

    public Meeting(Date dateDebut,Date dateFin,User clientMeeting, ArrayList<Invitation> listInvitation, Topic meetingTopic, Integer id) {

        this.clientMeeting = clientMeeting;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listInvitation = listInvitation;
        this.meetingTopic = meetingTopic;
        this.id = id;

    }

    public Meeting(){
        this.clientMeeting = null;
        this.dateDebut = null;
        this.dateFin = null;
        this.listInvitation = null;
        this.meetingTopic = null;
        this.id = null;
    }

    //Getters and setters

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public User getClientMeeting() {
        return clientMeeting;
    }

    public void setDateDebut(Date date) {
        this.dateDebut = date;
    }

    public void setDateFin(Date date){
        this.dateFin = date;
    }

    public void setDuration(Date dateFin) {
        this.dateFin = dateFin;
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

    public Topic getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(Topic meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
