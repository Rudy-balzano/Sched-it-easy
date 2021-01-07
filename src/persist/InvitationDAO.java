package persist;

import core.Invitation;
import core.Meeting;
import core.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This interface declares the methods used by a InvitationDAO that manipulates invitations related persistent data.
 */
public interface InvitationDAO {

    /**
     * Makes a new invitation persistent.
     * @param invitedUser the invited user.
     * @param state the invitation's state (0 : not yet answered, 1 : accepted, -1 : declined)
     * @param meetingInvitation the related meeting
     * @return boolean that indicates if the operation was successful
     */
    boolean insert(User invitedUser, int state, Meeting meetingInvitation);

    /**
     * Searches into the database all the invitations that the given user hasn't yet accepted nor declined.
     * @param username the user's username
     * @return a collection of the matching invitations
     */
    ArrayList<Invitation> findAllInvitation(String username);

    /**
     * Searches into the database all the invitations that the given user has except the ones he declined.
     * @param username the user's username.
     * @return a collection of Invitations that match the given requirements.
     */
    ArrayList<Invitation> findInvitation(String username);

    /**
     * Retrieve an invitation concerning the given user and meeting.
     * @param invitedUser the related user.
     * @param meetingInvitation the related meeting.
     * @return an Invitation that matches the given user and meeting.
     */
    Invitation findBy(User invitedUser, Meeting meetingInvitation);

    /**
     * Searches into the database the usernames of the users invited to a given meeting.
     * @param idMeeting id of the Meeting to search invited users.
     * @return a collection of the invited users to the Meeting that matches the given id.
     */
    Collection<String> getInvitedUsers(int idMeeting);

}
