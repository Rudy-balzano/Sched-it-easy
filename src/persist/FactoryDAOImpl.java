package persist;

public class FactoryDAOImpl implements FactoryDAO{

    /**
     * Singleton attribute
     */
    private static FactoryDAOImpl factoryDAOImpl;

    private FactoryDAOImpl(){
        this.factoryDAOImpl = this;
    }



    @Override
    public UserDAO createUserDAO() {

        return new MySQLUserDAO();

    }

    @Override
    public MeetingDAO createMeetingDAO() {

        return new MySQLMeetingDAO();
    }

    @Override
    public InvitationDAO createInvitationDAO() {

        return new MySQLInvitationDAO();
    }

    @Override
    public RoomDAO createRoomDAO() {
        return new MySQLRoomDAO();
    }
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




    /**
     * Singleton getInstance()
     */
    public static FactoryDAOImpl getInstance() {
        if(factoryDAOImpl != null){
            return factoryDAOImpl;
        }else{
            return new FactoryDAOImpl();
        }
    }

}
