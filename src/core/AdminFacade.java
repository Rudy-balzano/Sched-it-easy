package core;

import persist.AdminDAO;
import persist.FactoryDAOImpl;

/**
 * This class is the Facade for an Admin
 * @version 1.0
 * @see gui.controllers.AdminSeeAccountController
 */
public class AdminFacade {

    /**
     * adminDAO
     * @see AdminFacade#changeAccount(String, String)
     */
    private final AdminDAO adminDAO;

    /**
     * Constructor of AdminFacade
     * Using FactoryDAO
     */
    public AdminFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.adminDAO = factoryDAO.createAdminDAO();
    }

    /**
     * Function to update informations about the admin
     * insert the new admin and delete the old one.
     * Return a boolean if it has been inserted or not.
     * @param lastName
     * @param firstName
     * @return True if the new admin has been updated and False if not. (Info if admin has modify his account.)
     */
    public boolean changeAccount(String lastName, String firstName){
        Admin old = adminDAO.findByUsername(SessionFacade.getConnectedAdmin().getUsername());
        Admin nw = new Admin();

        nw.setUsername(old.getUsername());
        if(!firstName.isBlank()){nw.setFirstName(firstName);}else{nw.setFirstName(old.getFirstName());}
        if(!lastName.isBlank()){nw.setLastName(lastName);}else{nw.setLastName(old.getLastName());}
        nw.setPassword(old.getPassword());

        adminDAO.deleteAdmin(old.getUsername());
        return adminDAO.insertAdmin(nw.getUsername(), nw.getFirstName(), nw.getLastName(), nw.getPassword());
    }
}
