package gui.controllers;

import gui.Main;
import gui.roots.Roots;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
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
}
