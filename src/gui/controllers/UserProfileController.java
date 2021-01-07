package gui.controllers;

import core.ProfileManagementFacade;
import core.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Class controller for the user profile
 * @author emilie
 * @version 1.0
 */
public class UserProfileController implements AlertShower{
    /**
     * Textfield for the firstname
     */
    @FXML
    private TextField firstname;
    /**
     * Textfield for the lastname
     */
    @FXML
    private TextField lastname;

    /**
     * Function used to update the user profile
     */
    @FXML
    public void submitChanges(){
        ProfileManagementFacade facade = new ProfileManagementFacade();
        boolean res = facade.changeAccount(firstname.getText(),lastname.getText());
        if(res) {
            showAlert(Alert.AlertType.CONFIRMATION, firstname.getScene().getWindow(), "Success!", "Profile updated successfully!");
        }else{
            showAlert(Alert.AlertType.ERROR, firstname.getScene().getWindow(), "Failed!", "Failed to update profile...");
        }
    }
}
