package core;

import persist.FactoryDAOImpl;
import persist.UserDAO;

public class ProfileManagementFacade {
    private final UserDAO userDAO;

    public ProfileManagementFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
    }

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
