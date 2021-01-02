package core;

import persist.AdminDAO;
import persist.FactoryDAOImpl;

public class AdminFacade {

    private final AdminDAO adminDAO;

    public AdminFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.adminDAO = factoryDAO.createAdminDAO();
    }

    public void changeAccount(String username, String lastName, String firstName){
        Admin old = adminDAO.findByUsername(SessionFacade.getConnectedAdmin().getUsername());
        Admin nw = new Admin();

        if(!username.isBlank()){nw.setUsername(username);}else{nw.setUsername(old.getUsername());}
        if(!firstName.isBlank()){nw.setFirstName(firstName);}else{nw.setFirstName(old.getFirstName());}
        if(!lastName.isBlank()){nw.setLastName(lastName);}else{nw.setLastName(old.getLastName());}
        nw.setPassword(old.getPassword());

        adminDAO.deleteAdmin(old.getUsername());
        adminDAO.insertAdmin(nw.getUsername(), nw.getFirstName(), nw.getLastName(), nw.getPassword());
    }
}
