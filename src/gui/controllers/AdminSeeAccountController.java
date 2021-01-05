package gui.controllers;

import core.AdminFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class AdminSeeAccountController implements AlertShower{

    private final AdminFacade facade = new AdminFacade();

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;

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
