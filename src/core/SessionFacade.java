package core;


import persist.*;



/**
 *
 */
public class SessionFacade {

    private static User connectedUser;
    private static Admin connectedAdmin;
    private FactoryDAOImpl factoryDAO;
    private UserDAO userDAO;
    private AdminDAO adminDAO;


    /**
     * Default constructor
     */
    public SessionFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.adminDAO = factoryDAO.createAdminDAO();

    }

    /**
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
    public boolean register(String username, String firstName,String lastName, String password) {

        return userDAO.insertWaitingUser(username,firstName,lastName,password);

    }

    /**
     * @param username
     * @param password
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

    public static void logout(){
        connectedAdmin = null;
        connectedUser = null;
    }

    /**
     * fonction qui vérifie le mot de passe de l'utilisateur
     * @param userPassword
     * @param passwordEnter
     * @return
     */
    private Boolean verification(String userPassword, String passwordEnter){
        Boolean check = false;

        if(userPassword.equals(passwordEnter)){
            check = true;
        }
        return check;
    }

    /**
     *
     */
    public String getUserFirstName() {
        return connectedUser.getFirstName();
    }

    /**
     *
     */
    public String getUserLastName() {
        return connectedUser.getLastName();
    }

    public static User getConnectedUser() { return connectedUser; }

    public String getAdminFirstName(){
        return connectedAdmin.getFirstName();
    }

    public String getAdminLastName(){
        return connectedAdmin.getLastName();
    }

    public static Admin getConnectedAdmin() { return connectedAdmin;}

    public static void setConnectedAdmin() { SessionFacade.connectedAdmin = null;}

    public static void setConnectedUser() { SessionFacade.connectedUser = null;}
}
