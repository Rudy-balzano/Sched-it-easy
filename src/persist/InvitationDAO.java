package persist;

import core.Meeting;
import core.User;

public interface InvitationDAO {


    public boolean insert(User invitedUser, int state, Meeting meetingInvitation);
}
