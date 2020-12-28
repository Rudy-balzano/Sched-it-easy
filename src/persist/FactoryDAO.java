package persist;

import core.Topic;

public interface FactoryDAO {
    /**
     *
     */
    public UserDAO createUserDAO();

    public MeetingDAO createMeetingDAO();

    public InvitationDAO createInvitationDAO();

    public RoomDAO createRoomDAO();
    public TopicDAO createTopicDAO();


}
