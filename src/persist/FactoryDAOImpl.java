package persist;

/**
 * The FactoryDAOImpl class implements the FacotryDAO interface and is used to create DAOs that manipulate persistent data.
 */
public class FactoryDAOImpl implements FactoryDAO{

    /**
     * Singleton attribute.
     */
    private static FactoryDAOImpl factoryDAOImpl;

    /**
     * Constructs the FactoryDAOImpl.
     */
    private FactoryDAOImpl(){
        factoryDAOImpl = this;
    }


    @Override
    public UserDAO createUserDAO() { return new MySQLUserDAO(); }
    @Override
    public MeetingDAO createMeetingDAO() { return new MySQLMeetingDAO(); }
    @Override
    public InvitationDAO createInvitationDAO() { return new MySQLInvitationDAO(); }
    @Override
    public RoomDAO createRoomDAO() {
        return new MySQLRoomDAO();
    }
    @Override
    public TopicDAO createTopicDAO() {
        return new MySQLTopicDAO();
    }
    @Override
    public EquipmentDAO createEquipmentDAO() {
        return new MySQLEquipmentDAO();
    }
    @Override
    public AdminDAO createAdminDAO(){
        return new MySQLAdminDAO();
    }
    @Override
    public GroupDAO createGroupDAO() { return new MySQLGroupDAO();}


    /**
     * Singleton method getInstance() that ensures that only one instance of this class is instantiated at the same time.
     */
    public static FactoryDAOImpl getInstance() {
        if(factoryDAOImpl != null){
            return factoryDAOImpl;
        }else{
            return new FactoryDAOImpl();
        }
    }

}
