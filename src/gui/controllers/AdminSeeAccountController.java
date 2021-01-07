package gui.controllers;

import core.AdminFacade;
import core.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Class controller to see the account of an admin
 * @author emilie
 * @version 1.0
 */
public class AdminSeeAccountController implements AlertShower{

    /**
     * adminFacade
     */
    private final AdminFacade facade = new AdminFacade();


    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;

    /**
     * function used to initialize the View
     */
    public void initialize(){
       // username.setText(SessionFacade.getConnectedAdmin().getUsername());
        firstname.setText(SessionFacade.getConnectedAdmin().getFirstName());
        lastname.setText(SessionFacade.getConnectedAdmin().getLastName());
    }

    /**
     * function used update the profile of the Admin
     */
    @FXML
    public void submitChanges(){
        boolean res = facade.changeAccount(lastname.getText(),firstname.getText());
        if(res) {
            showAlert(Alert.AlertType.CONFIRMATION, firstname.getScene().getWindow(), "Success!", "Profile updated successfully!");
        }else{
            showAlert(Alert.AlertType.ERROR, firstname.getScene().getWindow(), "Failed!", "Failed to update profile...");
        }

    }
}
