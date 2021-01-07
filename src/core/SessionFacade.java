package core;


import persist.*;



/**
 * Class that represents a facade for a session
 * @author Baptiste Pierre Rudy Emilie
 * @version 1.0
 */
public class SessionFacade {
    /**
     * the connected user
     */
    private static User connectedUser;
    /**
     * the connected admin
     */
    private static Admin connectedAdmin;
    /**
     * factoryDAO
     */
    private FactoryDAOImpl factoryDAO;
    /**
     * userDAO
     */
    private UserDAO userDAO;
    /**
     * adminDAO
     */
    private AdminDAO adminDAO;


    /**
     * Default constructor of sessionFacade
     */
    public SessionFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.adminDAO = factoryDAO.createAdminDAO();

    }

    /**
     * function used to register an user
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @return True if the user is registered, false if not
     */
    public boolean register(String username, String firstName,String lastName, String password) {

        return userDAO.insertWaitingUser(username,firstName,lastName,password);

    }

    /**
     * Function used to logged someone into the application
     * @param username
     * @param password
     * @return 1 if an admin is logged, 0 if an user is logged, an -1 if no one is logged
     */
    public int login(String username, String password) {

        int check = -1;

        // on récupère les infos du user : username, password, firstname, lastname
        connectedUser = userDAO.findByUsername(username);
        connectedAdmin = adminDAO.findByUsername(username);

        // si on trouve le user dans la db
        if (connectedUser.getUserName() != null && verification(connectedUser.getPassword(), password)){
            check = 0;
        }
        else if (connectedAdmin.getUsername() != null && verification(connectedAdmin.getPassword(), password)) {
            check = 1;
        }
        else {
            System.out.println("Mauvais mot de passe");
        }

        return check;

    }

    /**
     * Function used to lougout the user
     */
    public static void logout(){
        connectedAdmin = null;
        connectedUser = null;
    }

    /**
     * Function used to check the password of the user
     * @param userPassword
     * @param passwordEnter
     * @return True if the password is equals, false if not.
     */
    private Boolean verification(String userPassword, String passwordEnter){
        Boolean check = false;

        if(userPassword.equals(passwordEnter)){
            check = true;
        }
        return check;
    }

    /**
     * return the firstname of the connected user
     * @return the firstname of the connected user
     */
    public String getUserFirstName() {
        return connectedUser.getFirstName();
    }

    /**
     * return the lastname of the connected user
     * @return the lastname of the connected user
     */
    public String getUserLastName() {
        return connectedUser.getLastName();
    }

    /**
     * return the connected user
     * @return the connected user
     */
    public static User getConnectedUser() { return connectedUser; }

    /**
     * return the firstname of the connectedAdmin
     * @return the firstname of the connectedAdmin
     */
    public String getAdminFirstName(){
        return connectedAdmin.getFirstName();
    }

    /**
     * return the lastname of the connectedAdmin
     * @return the lastname of the connectedAdmin
     */
    public String getAdminLastName(){
        return connectedAdmin.getLastName();
    }

    /**
     * return the connected admin
     * @return the connected admin
     */
    public static Admin getConnectedAdmin() { return connectedAdmin;}

    /**
     * Function used to set connected admin to null
     */
    public static void setConnectedAdmin() { SessionFacade.connectedAdmin = null;}

    /**
     * Function used to set connected user to null
     */
    public static void setConnectedUser() { SessionFacade.connectedUser = null;}
}
