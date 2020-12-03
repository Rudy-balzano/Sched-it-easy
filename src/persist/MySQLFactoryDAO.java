package persist;

public class MySQLFactoryDAO implements FactoryDAO{

    /**
     * Singleton attribute
     */
    private static MySQLFactoryDAO mySQLFactoryDAO;

    private MySQLFactoryDAO(){
        this.mySQLFactoryDAO = this;
    }



    @Override
    public MySQLUserDAO createUserDAO() {
        return new MySQLUserDAO();
    }

    /**
     * Singleton getInstance()
     */
    public static MySQLFactoryDAO getInstance() {
        if(mySQLFactoryDAO != null){
            return mySQLFactoryDAO;
        }else{
            return new MySQLFactoryDAO();
        }
    }


}
