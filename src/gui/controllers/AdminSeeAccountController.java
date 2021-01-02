package gui.controllers;

import core.AdminFacade;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AdminSeeAccountController {

    private final AdminFacade facade = new AdminFacade();

    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;

    @FXML
    public void submitChanges(){
        facade.changeAccount(username.getText(),lastname.getText(),firstname.getText());
    }
}
