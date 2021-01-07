package persist;

/**
 * This interface implements the method that create new DAOs.
 */
public interface FactoryDAO {
    /**
     * Creates a UserDAO that manipulates users related persistent data.
     * @return a UserDAO
     */
    UserDAO createUserDAO();

    /**
     * Creates a MeetingDAO that manipulates meetings related persistent data.
     * @return a MeetingDAO
     */
    MeetingDAO createMeetingDAO();

    /**
     * Creates an InvitationDAO that manipulates invitations related persistent data.
     * @return an InvitationDAO.
     */
    InvitationDAO createInvitationDAO();

    /**
     * Creates a RoomDAO that manipulates rooms related persistent data.
     * @return a RoomDAO.
     */
    RoomDAO createRoomDAO();

    /**
     * Creates a TopicDAO that manipulates topics related persistent data.
     * @return a TopicDAO.
     */
    TopicDAO createTopicDAO();

    /**
     * Creates an EquipmentDAO that manipulates equipments related persistent data.
     * @return an EquipmentDAO.
     */
    EquipmentDAO createEquipmentDAO();

    /**
     * Creates an AdminDAO that manipulates admins related persistent data.
     * @return an AdminDAO.
     */
    AdminDAO createAdminDAO();

    /**
     * Creates a GroupDAO that manipulates groups related persistent data.
     * @return a GroupDAO.
     */
    GroupDAO createGroupDAO();

}
