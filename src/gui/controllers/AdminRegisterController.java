package gui.controllers;

import core.AdminAccountManagementFacade;
import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class AdminRegisterController implements AlertShower{
    @FXML
    private TextField username;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Button registerButton;

    public void switchToAdminUsersManagementView(){
        try {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminUserManagementRoot))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleRegister(ActionEvent actionEvent) {
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
            Boolean register = false;
            Window owner = registerButton.getScene().getWindow();

            register = facade.registerNewAdmin(Username, Firstname, Lastname, Password);

            if (register){
                System.out.println("New administrator registered");
                this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Succes","Admin successfully registered !");
                //brings the admin back to users management page + success message?
                switchToAdminUsersManagementView();
            }
            else {
                System.out.println("Username already exist !");
                this.showAlert(Alert.AlertType.ERROR,owner,"Error","Impossible to register, username already exists...");
            }
        }


    }
}
