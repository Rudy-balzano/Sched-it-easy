package gui.controllers;

import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Class Controller to register
 * @author baptiste rudy pierre emilie
 * @version 1.0
 */
public class RegisterController implements AlertShower {
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
     * Button to Login
     */
    @FXML
    private Button loginButton;
    /**
     * Button to register
     */
    @FXML
    private Button registerButton;

    /**
     * Function used to go to the login page
     * @param actionEvent
     * @throws IOException
     */
    public void switchToLoginView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource(Roots.loginRoot));
        Scene loginView = new Scene(loginRoot);
        Main.scheditWindow.setScene(loginView);
    }

    /**
     * Function used to register a visitor
     * @param actionEvent
     */
    public void handleRegister(ActionEvent actionEvent) {
        SessionFacade session = new SessionFacade();

        String Username = username.getText();
        String Firstname = firstname.getText();
        String Lastname = lastname.getText();
        String Password = password.getText();
        String ConfirmPassword = confirmPassword.getText();
        Window owner = registerButton.getScene().getWindow();
        if (!Password.equals(ConfirmPassword) ){
            System.out.println("password and confirmPassword are different !");
            this.showAlert(Alert.AlertType.ERROR,owner,"Error","Password and confirmPassword are different, impossible to register");
            password.setText("");
            confirmPassword.setText("");
        }
        else {
            Boolean register = false;

            register = session.register(Username, Firstname, Lastname, Password);

            if (register){
                System.out.println("Waiting to be accept by the administrator");
                this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Username already exist, impossible to register");
            }
            else {
                System.out.println("Username already exist !");
                this.showAlert(Alert.AlertType.ERROR,owner,"Error","Username already exist, impossible to register");

            }
        }


    }
}
