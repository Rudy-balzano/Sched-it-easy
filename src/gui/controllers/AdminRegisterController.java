package gui.controllers;

import core.AdminAccountManagementFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Class Controller to register an admin
 * @version 1.0
 */
public class AdminRegisterController implements AlertShower{
    /**
     * Textfield for username
     */
    @FXML
    private TextField username;
    /**
     * Textfield for firstname
     */
    @FXML
    private TextField firstname;
    /**
     * Textfield for lastname
     */
    @FXML
    private TextField lastname;
    /**
     * Textfield for password
     */
    @FXML
    private TextField password;
    /**
     * Textfield for confirmPassword
     */
    @FXML
    private TextField confirmPassword;
    /**
     * Button for register
     */
    @FXML
    private Button registerButton;

    /**
     * Function used to switch to the Admin View
     */
    public void switchToAdminUsersManagementView(){
        try {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminUserManagementRoot))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function used to go back to the admin view, when clicking on cancel
     */
    public void handleCancel(){
        switchToAdminUsersManagementView();
    }

    /**
     * Function used to register a new admin
     */
    public void handleRegister() {
        AdminAccountManagementFacade facade = new AdminAccountManagementFacade();

        String Username = username.getText();
        String Firstname = firstname.getText();
        String Lastname = lastname.getText();
        String Password = password.getText();
        String ConfirmPassword = confirmPassword.getText();

        if (!Password.equals(ConfirmPassword) ){
            System.out.println("password and confirmPassword are different !");
            password.setText("");
            confirmPassword.setText("");
        }
        else {
            boolean register;
            Window owner = registerButton.getScene().getWindow();

            register = facade.registerNewAdmin(Username, Firstname, Lastname, Password);

            if (register){
                System.out.println("New administrator registered");
                this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Admin successfully registered !");
                //brings the admin back to users management page + success message?
                switchToAdminUsersManagementView();
            }
            else {
                System.out.println("Username already exists !");
                this.showAlert(Alert.AlertType.ERROR,owner,"Error","Impossible to register, username already exists...");
            }
        }


    }
}
