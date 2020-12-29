package persist;

import core.Meeting;
import core.User;

public interface InvitationDAO {

    boolean insert(User invitedUser, int state, Meeting meetingInvitation);
}
