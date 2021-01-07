package core;

import persist.FactoryDAOImpl;
import persist.UserDAO;

/**
 * Class that represents a facade for the profile managment
 * @author emilie jean
 * @version 1.0
 * @see gui.controllers.UserProfileController
 */
public class ProfileManagementFacade {
    /**
     * UserDAO
     */
    private final UserDAO userDAO;

    /**
     * Constructor of ProfileManagementFacade
     */
    public ProfileManagementFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
    }

    /**
     * Function used to modify account informations
     * @param firstName
     * @param lastName
     * @return true if the account has been modified, False if not
     */
    public boolean changeAccount(String firstName,String lastName){
        User oldUser = userDAO.findByUsername(SessionFacade.getConnectedUser().getUserName());

        String newFirstName;
        String newLastName;
        String username = oldUser.getUserName();

        if(!firstName.isBlank()){
            newFirstName = firstName;
        }else{
            newFirstName = oldUser.getFirstName();
        }
        if(!lastName.isBlank()){
            newLastName = lastName;
        }else{
            newLastName = oldUser.getLastName();
        }

        return userDAO.modifyUser(username,newFirstName,newLastName);
    }
}
