package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserProfileController {

    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;

    @FXML
    public void submitChanges(){
        System.out.println("This does nothing");
        //TODO
        //facade.changeAccount(username.getText(),lastname.getText(),firstname.getText());

    }
}
