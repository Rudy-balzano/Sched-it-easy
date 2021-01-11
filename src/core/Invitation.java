package core;

/**
 * Class that represents an invitation to a meeting
 * @version 1.0
 */
public class Invitation {
    /**
     * The user that have been invited
     * @see Invitation#getInvitedUser()
     * @see Invitation#setInvitedUser(User)
     */
    private User invitedUser;
    /**
     * the state of the invitation. 0 = no responses yet, 1 = user invited will be present, -1 = user invited won't be present
     * @see Invitation#getState()
     * @see Invitation#setState(int)
     */
    private int state;
    /**
     * Meeting that the invitation is about
     * @see Invitation#setMeetingInvitation(Meeting)
     * @see Invitation#getMeetingInvitation()
     */
    private Meeting meetingInvitation;

    /**
     * Constructor null of Invitation
     */
    public Invitation() {

    }

    /**
     * Constructor of Invitation
     * @param invitedUser
     * @param state
     * @param meetingInvitation
     * @see Invitation#invitedUser
     * @see Invitation#state
     * @see Invitation#meetingInvitation
     */
    public Invitation(User invitedUser, int state, Meeting meetingInvitation){

        this.invitedUser = invitedUser;
        this.state = state;
        this.meetingInvitation = meetingInvitation;

    }

    //Getters and setters

    /**
     * Return the User that is invited to this invitation
     * @return the User that is invited to this invitation
     */
    public User getInvitedUser() {  return invitedUser;
    }

    /**
     * Set the User that is invited to this invitation
     * @param invitedUser
     */
    public void setInvitedUser(User invitedUser) {
        this.invitedUser = invitedUser;
    }

    /**
     * Return the state of the invitation
     * @return the state of the invitation
     */
    public int getState() {
        return state;
    }

    /**
     * Set the state of the invitation
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * return the meeting that the invitation is about
     * @return the meeting that the invitation is about
     */
    public Meeting getMeetingInvitation() {
        return meetingInvitation;
    }

    /**
     * set the meeting that the invitation is about
     * @param meetingInvitation
     */
    public void setMeetingInvitation(Meeting meetingInvitation) {
        this.meetingInvitation = meetingInvitation;
    }

    //Methods

    /**
     * //TODO
     * @return the answer of the Invitation
     */
    public boolean answerInviation(){

        //TODO

        return true;
    }

    /**
     * Return the username of the invited user
     * @return the username of the invited user
     */
    public String getInvitedUser2 (){
        return invitedUser.getUserName();
    }
}
