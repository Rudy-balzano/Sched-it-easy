package persist;

public interface FactoryDAO {
    /**
     *
     */
    UserDAO createUserDAO();

    MeetingDAO createMeetingDAO();

    InvitationDAO createInvitationDAO();

    RoomDAO createRoomDAO();

    TopicDAO createTopicDAO();

    EquipmentDAO createEquipmentDAO();

    AdminDAO createAdminDAO();

    GroupDAO createGroupDAO();

}
