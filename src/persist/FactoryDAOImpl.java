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
