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

public class RegisterController {

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
    private Button loginButton;

    @FXML
    private Button registerButton;


    public void switchToLoginView(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource(Roots.loginRoot));
        Scene loginView = new Scene(loginRoot);
        Main.scheditWindow.setScene(loginView);
    }


    public void handleRegister(ActionEvent actionEvent) {
        SessionFacade session = new SessionFacade();

        String Username = username.getText();
        String Firstname = firstname.getText();
        String Lastname = lastname.getText();
        String Password = password.getText();
        String ConfirmPassword = confirmPassword.getText();

        if (Password != ConfirmPassword){
            System.out.println("password and confirmPassword are different !");
            password.setText("");
            confirmPassword.setText("");
        }
        else {
            Boolean register = false;

            register = session.register(Username, Firstname, Lastname, Password);

            if (register){
                System.out.println("Waiting to be accept by the administrator");
            }
            else {
                System.out.println("Username already exist !");
            }
        }


    }
}
