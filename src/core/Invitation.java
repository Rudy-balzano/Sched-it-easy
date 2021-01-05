package core;

public class Invitation {

    private User invitedUser;
    private int state;
    private Meeting meetingInvitation;

    public Invitation() {

    }
    public Invitation(User invitedUser, int state, Meeting meetingInvitation){

        this.invitedUser = invitedUser;
        this.state = state;
        this.meetingInvitation = meetingInvitation;

    }

    //Getters and setters

    public User getInvitedUser() {

        return invitedUser;
    }

    public void setInvitedUser(User invitedUser) {
        this.invitedUser = invitedUser;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Meeting getMeetingInvitation() {
        return meetingInvitation;
    }

    public void setMeetingInvitation(Meeting meetingInvitation) {
        this.meetingInvitation = meetingInvitation;
    }

    //Methods

    public boolean answerInviation(){

        //TODO

        return true;
    }
}
