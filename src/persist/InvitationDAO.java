package persist;

import core.Invitation;
import core.Meeting;
import core.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface InvitationDAO {

    boolean insert(User invitedUser, int state, Meeting meetingInvitation);
    ArrayList<Invitation> findAllInvitation(String username);
    ArrayList<Invitation> findInvitation(String username);
    Invitation findBy(User invitedUser, Meeting meetingInvitation);

}
