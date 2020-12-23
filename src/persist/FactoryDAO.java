package persist;

public interface FactoryDAO {
    /**
     *
     */
    public UserDAO createUserDAO();

    public MeetingDAO createMeetingDAO();

    public InvitationDAO createInvitationDAO();


}
