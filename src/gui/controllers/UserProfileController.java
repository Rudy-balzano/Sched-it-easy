package gui.controllers;

import core.ProfileManagementFacade;
import core.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class UserProfileController implements AlertShower{

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;

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
